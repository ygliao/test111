//$ac1="100019";
//        $pw1="jjl4yk11f82ce6c0f33a5c003f2fec56"; //票付通账号跟密码


import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class TestSoap {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 调用PublishWebService
        String wsdlUrl = "http://open.12301dev.com/openService/MXSE_beta.wsdl";//webservice地址
        String method = "Get_ScenicSpot_Info"; //调用的那个方法
        Object[] param = new Object[] { "100019","jjl4yk11f82ce6c0f33a5c003f2fec56",2633};//传递的参数值
        String namespaceUrl1 = "urn:PFTMX";//命名空间
        Class[] opReturnType1 = new Class[] { String[].class };//返回值类型
        String result = axis2RPCInvoke(wsdlUrl, method, param, namespaceUrl1,opReturnType1);
        System.out.println(result);
    }
    /**
     * RPC调用AXIS2 webservice
     *
     * @param wsdlUrl
     *            服务地址 如：http://192.168.0.1:2597/aixs2/services/jqservice?wsdl
     * @param methodName
     *            方法名 如<xs:element name="Receive">
     * @param parameter
     *            方法参数 如Object[] opArgs = new Object[] { param };
     * @param namespaceURI
     *            命名空间 如 ：targetNamespace="http://server.test.com.cn">
     * @param returnType
     *            返回类型 如字符串：Class[] opReturnType = new Class[] { String[].class
     *            };
     */
    public static String axis2RPCInvoke(String wsdlUrl, String methodName,
                                        Object[] parameter, String namespaceURI, Class[] returnType) {
        Object[] ret = null;
        RPCServiceClient serviceClient = null;
        try {
            /*
             * 此处RPCServiceClient 对象实例建议定义成类中的static变量 ，否则多次调用会出现连接超时的错误。
             */
            serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            EndpointReference targetEPR = new EndpointReference(wsdlUrl);
            options.setTo(targetEPR);
            QName opQName = new QName(namespaceURI, methodName);
            ret = serviceClient.invokeBlocking(opQName, parameter, returnType);
            // System.out.println(((String[]) ret[0])[0]);
        } catch (AxisFault e) {
            e.printStackTrace();
        }
        return ((String[]) ret[0])[0];
    }
}