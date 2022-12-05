/*
 * Copyright © 2018 2020 and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package it.nextworks.qameleon;


import it.nextworks.qameleon.qamProvisioningApp.nbi.TapiConnectivityServiceImpl;
import it.nextworks.qameleon.qamProvisioningApp.nbi.TapiPathComputationRest;
import it.nextworks.qameleon.qamProvisioningApp.sbi.TopologyAppServiceConsumer;
import org.opendaylight.mdsal.binding.api.*;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.QameleonTopologyService;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Context;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.ContextBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.TapiConnectivityService;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.TapiPathComputationService;
import org.opendaylight.yangtools.concepts.ObjectRegistration;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;


public class QAMeleonProvisioningAppStarter {

    private static final Logger LOG = LoggerFactory.getLogger(QAMeleonProvisioningAppStarter.class);

    private final DataBroker dataBroker;
    private final RpcProviderService rpcProviderService;
    private final RpcConsumerRegistry rpcConsumerRegistry;
    private final MountPointService mountPointService;
    private QameleonTopologyService qameleonTopologyService;
    private ObjectRegistration<TapiPathComputationService> objectRegistration;
    private ObjectRegistration<TapiConnectivityService> objectRegistration2;

    public QAMeleonProvisioningAppStarter(final DataBroker dataBroker, final RpcProviderService rpcProviderService, final MountPointService mountPointService, RpcConsumerRegistry rpcConsumerRegistry) {
        this.dataBroker = dataBroker;
        this.rpcProviderService = rpcProviderService;
        this.mountPointService = mountPointService;
        this.rpcConsumerRegistry = rpcConsumerRegistry;
    }

    private void initDataTree(){
        LOG.info("Starting (light) path data tree...");
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        InstanceIdentifier<Context> iidContext = InstanceIdentifier.builder(Context.class)
                .build();
        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iidContext).get();
            if (!data) {
                LOG.warn("No Tapi path context initialized.");
                final WriteTransaction wtx = dataBroker.newWriteOnlyTransaction();
                Context ctx = new ContextBuilder().build();
                wtx.put(LogicalDatastoreType.CONFIGURATION,iidContext,ctx);
                wtx.commit();
            } else {
                LOG.info("Tapi Context already initialized");
            }
            LOG.info("Data tree correctly initialized...");
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error while reading context from the data tree.");
            LOG.error(e.getMessage());
        } finally {
            transaction.close();
        }
    }
     public void init() {
        qameleonTopologyService = rpcConsumerRegistry.getRpcService(QameleonTopologyService.class);
        if(qameleonTopologyService==null){
            LOG.error("Unable to get Qameleon topology  service.");
            return;
        }
        new TopologyAppServiceConsumer(dataBroker, qameleonTopologyService);
        TapiPathComputationService tapiPathComputationService = new TapiPathComputationRest(dataBroker, mountPointService);
         objectRegistration = rpcProviderService.registerRpcImplementation(TapiPathComputationService.class, tapiPathComputationService);
         TapiConnectivityServiceImpl tapiConnectivityService = new TapiConnectivityServiceImpl(dataBroker, mountPointService);
         objectRegistration2 = rpcProviderService.registerRpcImplementation(TapiConnectivityService.class, tapiConnectivityService);
         LOG.info("Registered path computation service.");
         initDataTree();
    }


    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        LOG.info("Closing object registration.");
        if (objectRegistration != null)
            objectRegistration.close();

        if(objectRegistration2!=null)
        objectRegistration2.close();

        }
}