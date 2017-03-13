package com.doncat.jenkins.plugintest;
import hudson.Launcher;
import hudson.Extension;
import hudson.util.FormValidation;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.doncat.jenkins.core.DrawFileUtil;
import com.doncat.jenkins.core.SCMPath2LocalPathUtil;
import com.doncat.jenkins.svnapi.Diff;
import com.doncat.jenkins.utils.WarUtil;

import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Sample {@link Builder}.
 *
 * <p>
 * When the user configures the project and enables this builder,
 * {@link DescriptorImpl#newInstance(StaplerRequest)} is invoked
 * and a new {@link HelloWorldBuilder} is created. The created
 * instance is persisted to the project configuration XML by using
 * XStream, so this allows you to use instance fields (like {@link #name})
 * to remember the configuration.
 *
 * <p>
 * When a build is performed, the {@link #perform(AbstractBuild, Launcher, BuildListener)}
 * method will be invoked. 
 *
 * @author Kohsuke Kawaguchi
 */
public class HelloWorldBuilder extends Builder {

    private final String trunkPath;
    private final String branchPath;
    private final String localWorkspace;
    private final String packageName;
    public static SVNClientManager ourClientManager; 
    private final static Logger logger = Logger.getLogger("GetFileViaSVNList");

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public HelloWorldBuilder(String trunkPath, String branchPath, String localWorkspace, String packageName) {
		this.trunkPath = trunkPath;
		this.branchPath = branchPath;
		this.localWorkspace = localWorkspace;
		this.packageName = packageName;
	}

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
		//perform方法的返回值告诉jenkins当前步骤是否成功，如果失败了jenkins将放弃后续的步骤。
        // This is where you 'build' the project.
        // Since this is a dummy, we just say 'hello world' and call that a build.

        // This also shows how you can consult the global configuration of the builder
		//perform重载函数。构建的执行通过实现perform方法来进行自定义。每次执行编译时都会运行perform函数。它有三个参数：
		//Build参数是描述了当前任务的一次构建，通过它可以访问到一些比较重要的模型对象如：project当前项目的对象、workspace构建的工作空间、Result当前构建步骤的结果。
        //Launcher参数用于启动构建。
		//BuildListener该接口用于检查构建过程的状态（开始、失败、成功..），通过它可以在构建过程中发送一些控制台信息给jenkins。
//		if (getDescriptor().getUseFrench())//在这个例子中if..else..语句是向控制台端输出日志信息，其中name的信息由构造函数有关。
//            listener.getLogger().println("Bonjour, "+trunkPath+"!");
//        else
//            listener.getLogger().println("Hello, "+trunkPath+"!");
    	Diff svndiffHeadResult = new Diff();
		try {
			DAVRepositoryFactory.setup();
			ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
			String name = "user";
			String password = "********";
			ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions)options, name, password);
			svndiffHeadResult.getDiffResult(ourClientManager, trunkPath, SVNRevision.HEAD, branchPath, SVNRevision.HEAD, localWorkspace+"\\svndiffresult.txt");
		} catch (SVNException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		listener.getLogger().println("文件列表获取完毕，准备解压war包");
		WarUtil unzipWar = new WarUtil();
		String unzipPath = unzipWar.getFileNameWithNoSux(packageName);
		unzipWar.unzipWar(localWorkspace+"/"+packageName,localWorkspace+"/"+unzipPath);
		listener.getLogger().println("war包解压完毕，准备匹配文件");
		logger.info(localWorkspace+"\\"+unzipPath+";"+localWorkspace+"\\svndiffresult.txt"+";"+localWorkspace+"\\CorrectList.txt"+";"+localWorkspace+"\\notFoundList.txt");
		SCMPath2LocalPathUtil.scmPath2LocalPath(localWorkspace+"\\"+unzipPath, localWorkspace+"\\svndiffresult.txt", localWorkspace+"\\CorrectList.txt",localWorkspace+"\\notFoundList.txt");
		listener.getLogger().println("文件匹配完毕，准备抽取");
		DrawFileUtil.draw(localWorkspace+"\\CorrectList.txt",localWorkspace+"\\increaseFile",localWorkspace+"\\"+packageName,localWorkspace+"\\unzipPath",localWorkspace);
		listener.getLogger().println("增量包提取完毕，位置："+localWorkspace+"\\increaseFile");
        return true;
    }

	/**
     * We'll use this from the <tt>config.jelly</tt>.
     */
    public String getTrunkPath() {
		return trunkPath;
	}

	public String getBranchPath() {
		return branchPath;
	}

	public String getLocalWorkspace() {
		return localWorkspace;
	}

	public String getPackageName() {
		return packageName;
	}

	// Overridden for better type safety.
    // If your plugin doesn't really define any property on Descriptor,
    // you don't have to do this.
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    /**
     * Descriptor for {@link HelloWorldBuilder}. Used as a singleton.
     * The class is marked as public so that it can be accessed from views.
     *
     * <p>
     * See <tt>src/main/resources/hudson/plugins/hello_world/HelloWorldBuilder/*.jelly</tt>
     * for the actual HTML fragment for the configuration screen.
     */
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        /**
         * To persist global configuration information,
         * simply store it in a field and call save().
         *
         * <p>
         * If you don't want fields to be persisted, use <tt>transient</tt>.
         */
        private boolean useFrench;

        /**
         * In order to load the persisted global configuration, you have to 
         * call load() in the constructor.
         */
        public DescriptorImpl() {
            load();
        }

        /**
         * Performs on-the-fly validation of the form field 'name'.
         *
         * @param value
         *      This parameter receives the value that the user has typed.
         * @return
         *      Indicates the outcome of the validation. This is sent to the browser.
         *      <p>
         *      Note that returning {@link FormValidation#error(String)} does not
         *      prevent the form from being saved. It just means that a message
         *      will be displayed to the user. 
		 在DescriptorImpl内部类中doCheckName(@QueryParameter String value)方法，在光标不再在输入框时，将执行这个方法，
		 其中输入框的输入值以value值传入，在这个函数里可以进行验证，是否符合输入条件。
         */
        public FormValidation doCheckTrunkPath(@QueryParameter String trunkPath)
                throws IOException, ServletException {
            if (trunkPath.length() == 0)
                return FormValidation.error("请填写主干url");
            return FormValidation.ok();
        }
        public FormValidation doCheckBranchPath(@QueryParameter String branchPath)
                throws IOException, ServletException {
            if (branchPath.length() == 0)
                return FormValidation.error("请填写分支url");
            return FormValidation.ok();
        }
        public FormValidation doCheckLocalWorkspace(@QueryParameter String localWorkspace)
                throws IOException, ServletException {
            if (localWorkspace.length() == 0)
                return FormValidation.error("请填写程序包所在目录，如“${WORKSPACE}/target”");
            return FormValidation.ok();
        }
        public FormValidation doCheckPackageName(@QueryParameter String packageName)
                throws IOException, ServletException {
            if (packageName.length() == 0)
                return FormValidation.error("请填写包名，如HRBCMS.war");
            return FormValidation.ok();
        }

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }

        /**
         * This human readable name is used in the configuration screen.
         */
        public String getDisplayName() {//此方法返回的字符串作为pre-build step的名称。
            return "xxx的新插件";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            // To persist global configuration information,
            // set that to properties and call save().
            useFrench = formData.getBoolean("useFrench");
            // ^Can also use req.bindJSON(this, formData);
            //  (easier when there are many fields; need set* methods for this, like setUseFrench)
            save();
            return super.configure(req,formData);
        }

        /**
         * This method returns true if the global configuration says we should speak French.
         *
         * The method name is bit awkward because global.jelly calls this method to determine
         * the initial state of the checkbox by the naming convention.
         */
        public boolean getUseFrench() {
            return useFrench;
        }
    }
}