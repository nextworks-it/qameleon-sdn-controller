package it.nextworks.qameleon.sbi.netconf_driver.test;

import it.nextworks.qameleon.sbi.netconf_driver.NetconfDriver;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.binding.api.RpcConsumerRegistry;
import org.opendaylight.yang.gen.v1.urn.example.ops.reboot.rev160707.ExampleOpsService;
import org.opendaylight.yang.gen.v1.urn.example.ops.reboot.rev160707.RebootInput;
import org.opendaylight.yang.gen.v1.urn.example.ops.reboot.rev160707.RebootInputBuilder;
import org.opendaylight.yang.gen.v1.urn.example.ops.reboot.rev160707.RebootOutput;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/*
* This test class invokes the RPC defined in this YANG model.
* module example-ops {
     namespace "urn:example-ops:reboot";
     prefix "ops";


     revision "2016-07-07" {
       description "Initial version.";
       reference "example document.";
     }


     rpc reboot {
       description "Reboot operation.";
       input {
         leaf delay {
           type uint32;
           units "seconds";
           default 0;
           description
             "Delay in seconds.";
         }
         leaf message {
           type string;
           description
             "Log message.";
         }
       }
     }
   }
   *
* */
public class NetconfDriverRpcTest extends NetconfDriver {
    private static final Logger LOG = LoggerFactory.getLogger(NetconfDriverRpcTest.class);

    public NetconfDriverRpcTest(MountPointService mountPointService,String nodeId) {
        super(mountPointService, nodeId);
    }


    public boolean testRemoteRpcReboot(){
        RpcConsumerRegistry rpcConsumerRegistry = getRpcConsumerRegistry();
        ExampleOpsService exampleOpsService = rpcConsumerRegistry.getRpcService(ExampleOpsService.class);
        RebootInput rebootInput = new RebootInputBuilder().setDelay(new Long(300)).setMessage("message").build();
        try {
            RpcResult<RebootOutput> rpcResult = exampleOpsService.reboot(rebootInput).get();
            return rpcResult.isSuccessful();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

}
