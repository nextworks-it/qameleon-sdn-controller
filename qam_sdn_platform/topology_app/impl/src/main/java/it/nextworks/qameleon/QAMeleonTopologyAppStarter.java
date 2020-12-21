/*
 * Copyright © 2018 2020 and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package it.nextworks.qameleon;


import it.nextworks.generic.topologyApp.nbi.NxwTapiTopologyServiceImpl;
import it.nextworks.generic.topologyApp.nbi.TapiTopologyServiceImpl;
import it.nextworks.qameleon.topologyApp.nbi.QameleonTopologyAppRest;
import org.opendaylight.mdsal.binding.api.*;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.NxwTapiTopologyService;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.QamTopologyCont;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.QamTopologyContBuilder;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.QameleonTopologyService;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Context;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.ContextBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.TapiTopologyService;
import org.opendaylight.yangtools.concepts.ObjectRegistration;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;


//TODO change my class name in the refactoring.
public class QAMeleonTopologyAppStarter {

    private static final Logger LOG = LoggerFactory.getLogger(QAMeleonTopologyAppStarter.class);

    private final DataBroker dataBroker;
    final RpcProviderService rpcProviderService;
    final RpcConsumerRegistry rpcConsumerRegistry;
    final MountPointService mountPointService;

    private ObjectRegistration<NxwTapiTopologyServiceImpl> topologyApp;
    private ObjectRegistration<QameleonTopologyService> qamTopServiceObjReg;
    private ObjectRegistration<TapiTopologyService> tapiTopologyObjReg;

    public QAMeleonTopologyAppStarter(final DataBroker dataBroker, final RpcProviderService rpcProviderService, final MountPointService mountPointService,RpcConsumerRegistry rpcConsumerRegistry) {
        this.dataBroker = dataBroker;
        this.rpcProviderService = rpcProviderService;
        this.mountPointService = mountPointService;
        this.rpcConsumerRegistry = rpcConsumerRegistry;
    }

    private void initQameleonDataTree(){
        LOG.info("Initing Qam topology data tree...");
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        InstanceIdentifier<QamTopologyCont> iidContext = InstanceIdentifier.builder(QamTopologyCont.class)
                .build();
        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iidContext).get();
            if (!data) {
                LOG.warn("No qam topology initialized.");
                final WriteTransaction wtx = dataBroker.newWriteOnlyTransaction();
                QamTopologyCont ctx = new QamTopologyContBuilder().build();
                wtx.put(LogicalDatastoreType.CONFIGURATION,iidContext,ctx);
                wtx.commit();
            } else {
                LOG.info("Qam topology Builder already initialized");
            }
            LOG.info("Data tree correctly initialized...");
        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error while reading qam topology from the data tree.");
            LOG.error(e.getMessage());
        } finally {
            transaction.close();
        }
    }

    private void initTapiTopologyDataTree(){
        LOG.info("Initing Tapi topology data tree...");
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        InstanceIdentifier<Context> iidContext = InstanceIdentifier.builder(Context.class)
                .build();
        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iidContext).get();
            if (!data) {
                LOG.warn("No Tapi topology path context initialized.");
                final WriteTransaction wtx = dataBroker.newWriteOnlyTransaction();
                Context context = new ContextBuilder().build();
                wtx.put(LogicalDatastoreType.CONFIGURATION,iidContext,context);
                wtx.commit();
            } else {
                LOG.info("Tapi topology context already initialized");
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
        LOG.info("Initing topology application.");
        NxwTapiTopologyServiceImpl nxwTapiTopologyServiceImpl = new NxwTapiTopologyServiceImpl(dataBroker);
        topologyApp = rpcProviderService.registerRpcImplementation(NxwTapiTopologyService.class, nxwTapiTopologyServiceImpl);

        TapiTopologyService tapiTopologyService = new TapiTopologyServiceImpl(dataBroker);
        tapiTopologyObjReg = rpcProviderService.registerRpcImplementation(TapiTopologyService.class, tapiTopologyService);

        initTapiTopologyDataTree();

        QameleonTopologyAppRest qameleonTopologyAppRest = new QameleonTopologyAppRest(dataBroker, (TapiTopologyServiceImpl) tapiTopologyService, nxwTapiTopologyServiceImpl, mountPointService);
        qamTopServiceObjReg = rpcProviderService.registerRpcImplementation(QameleonTopologyService.class, qameleonTopologyAppRest);

        initQameleonDataTree();

    }


    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        if( topologyApp != null){
            //TODO disconnect from devices
            topologyApp.close();
        }
        if(qamTopServiceObjReg!=null)
            qamTopServiceObjReg.close();


        if(tapiTopologyObjReg!=null)
            tapiTopologyObjReg.close();
        LOG.info("Topology app Closed");
    }

}