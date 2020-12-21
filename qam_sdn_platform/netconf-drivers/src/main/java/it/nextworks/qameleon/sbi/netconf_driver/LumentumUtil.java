package it.nextworks.qameleon.sbi.netconf_driver;

import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.DistinguishedName;

import java.util.HashMap;

    public  class LumentumUtil {
        public static final  String MUX_IN_PORT_PREFIX = "41";
        public static final  String DEMUX_OUT_PORT_PREFIX = "52";

        public static String leftPadZero(int number){
            if(number<10){
                return "0"+number;
            }
            return String.valueOf(number);
        }

        public static HashMap<String,Integer> dnToHasMap(DistinguishedName dn){
            HashMap<String,Integer> dnHashMap = new HashMap<>();
            String [] elements = dn.getValue().split(";");
            for(String element: elements){
                String [] nameValuePair = element.split("=");
                String name = nameValuePair[0];
                String value = nameValuePair[1];
                dnHashMap.put(name,Integer.valueOf(value));
            }
            return dnHashMap;
        }
}
