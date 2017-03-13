import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unicompayment.fip.common.utils.network.HttpUtils;


public class CommonTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String url = "http://192.168.8.64:8081/pay/servlet/HttpAPIServlet.htm?reqCharSet=UTF-8";
		Map m = new HashMap();
		m.put("servCode", "0001");
		m.put("cliCode", "CucPay");
		m.put("tradType", "B2BWY");
		List<String> l = HttpUtils.URLPost(url, m, "utf-8");
		List<String> l0 = new ArrayList<String>();
		//for(String s:l){  {\"COBANKNO\":\"ICBC_B2B\",\"COBANKNAME\":\"涓..宸ュ.?惰?\",\"LOGO\":\"ICBC\"},{\"COBANKNO\":\"CCB_B2B\",\"COBANKNAME\":\"寤鸿.?惰?\",\"LOGO\":\"CCB\"},{\"COBANKNO\":\"ABC_B2B\",\"COBANKNAME\":\"涓..?.??惰?\",\"LOGO\":\"ABC\"},{\"COBANKNO\":\"CMB_B2B\",\"COBANKNAME\":\"?..?惰?\",\"LOGO\":\"CMB\"},{\"COBANKNO\":\"BOC_B2B\",\"COBANKNAME\":\"涓..?惰?\",\"LOGO\":\"BOC\"},{\"COBANKNO\":\"ECITIC_B2B\",\"COBANKNAME\":\"涓.俊?惰?\",\"LOGO\":\"ECITIC\"},{\"COBANKNO\":\"SPDB_B2B\",\"COBANKNAME\":\"娴?.?惰?\",\"LOGO\":\"SPDB\"},{\"COBANKNO\":\"CMBC_B2B\",\"COBANKNAME\":\"姘..?惰?\",\"LOGO\":\"CMBC\"},{\"COBANKNO\":\"CIB_B2B\",\"COBANKNAME\":\"?翠??惰?\",\"LOGO\":\"CIB\"},{\"COBANKNO\":\"GDB_B2B\",\"COBANKNAME\":\"骞垮.?惰?\",\"LOGO\":\"GDB\"},{\"COBANKNO\":\"NBCB_B2B\",\"COBANKNAME\":\"瀹.尝?惰?\",\"LOGO\":\"NBCB\"},{\"COBANKNO\":\"BCCB_B2B\",\"COBANKNAME\":\"?.含?惰?\",\"LOGO\":\"BCCB\"},{\"COBANKNO\":\"HSB_B2B\",\"COBANKNAME\":\"寰藉.?惰?\",\"LOGO\":\"HSB\"},{\"COBANKNO\":\"HXB_B2B\",\"COBANKNAME\":\"?.??惰?\",\"LOGO\":\"HXB\"},{\"COBANKNO\":\"BOHB_B2B\",\"COBANKNAME\":\"娌冲.?惰?\",\"LOGO\":\"BOHB\"},{\"COBANKNO\":\"SPA_B2B\",\"COBANKNAME\":\"涓..骞冲.?惰?\",\"LOGO\":\"SPA\"},{\"COBANKNO\":\"BOCO_B2B\",\"COBANKNAME\":\"浜ら.?..琛.?\",\"LOGO\":\"BOCO\"},{\"COBANKNO\":\"BOT_B2B\",\"COBANKNAME\":\"澶╂触?惰?\",\"LOGO\":\"BOT\"}
			String s1 = "cliCode=fpep_out_face&tradType=B2BWY&bankList=[{\"COBANKNO\":\"SPA_B2B\",\"COBANKNAME\":\"涓..骞冲.?惰?\",\"LOGO\":\"SPA\"},{\"COBANKNO\":\"BOCO_B2B\",\"COBANKNAME\":\"浜ら.?..琛.?\",\"LOGO\":\"BOCO\"},{\"COBANKNO\":\"BOT_B2B\",\"COBANKNAME\":\"澶╂触?惰?\",\"LOGO\":\"BOT\"}]&resCode=0000&errDesc=";
//			if(s==null)
//				continue;
			String[] arr1 = s1.trim().split("[&]",-1);
//			if(arr1==null || arr1.length==0)
//				continue;
			for(String s0:arr1){
				if(s0==null)
					continue;
				s0 = s0.trim();
				if(s0.indexOf("{") < 0)
					continue;
				int start = s0.indexOf("{");
				int end = s0.lastIndexOf("}");
				if(start >= end)
					continue;
				s0 = s0.substring(start,end+1);
				for(int i=0;i<s0.length();i++){
					if(s0.charAt(i) == '{')
						start = i;
					if(s0.charAt(i) == '}'){
						if(start == -1)
							continue;
						String tmpStr = s0.substring(start+1,i);
						l0.add(tmpStr);
						start = -1;
					}
				}
			}
			System.out.println(s1);
		//}
		String logos = "";
		for(String s:l0){
			if(s==null)
				continue;
			String[] arr = s.trim().split("[,]",-1);
			if(arr==null || arr.length==0)
				continue;
			for(String s0:arr){
				if(s0==null)
					continue;
				if(s0.trim().toUpperCase().indexOf("\"LOGO\":") > -1){
					String logo = s0.trim().replaceAll("\"", "").replaceAll("LOGO:", "");
					logos += logo+",";
				}
			}
		}
		System.out.println(logos);
//		List<BankItem> tempBankOrgList = bankOrgQuery
//				.queryBank(FpepEnumConstants.B2B_FLAG_ALL);				
//		
//		if(tempBankOrgList != null){
//			bankOrgList = new ArrayList<BankItem>();
//			for (BankItem bi:tempBankOrgList) {
//				if(logos.indexOf(bi.getBankCode()+",") > -1){
//					bi.setB2bFlag(FpepEnumConstants.B2B_FLAG_YES);
//					bankOrgList.add(bi);
//				}
//			}
//		}
//		System.out.println(logos);
	}

}
