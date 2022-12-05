/*
 * This is automatically generated callbacks file
 * It contains 3 parts: Configuration callbacks, RPC callbacks and state data callbacks.
 * Do NOT alter function signatures or any structures unless you know exactly what you are doing.
 */

#include <stdlib.h>
#include <sys/inotify.h>
#include <libxml/tree.h>
#include <libnetconf_xml.h>
#include <stdio.h> 
#include <stdlib.h> 
#include "../ext_lib/socket_writer.h"


/* transAPI version which must be compatible with libnetconf */
int transapi_version = 6;

/* Signal to libnetconf that configuration data were modified by any callback.
 * 0 - data not modified
 * 1 - data have been modified
 */
int config_modified = 0;

/*
 * Determines the callbacks order.
 * Set this variable before compilation and DO NOT modify it in runtime.
 * TRANSAPI_CLBCKS_LEAF_TO_ROOT (default)
 * TRANSAPI_CLBCKS_ROOT_TO_LEAF
 */
const TRANSAPI_CLBCKS_ORDER_TYPE callbacks_order = TRANSAPI_CLBCKS_ORDER_DEFAULT;

/* Do not modify or set! This variable is set by libnetconf to announce edit-config's error-option
Feel free to use it to distinguish module behavior for different error-option values.
 * Possible values:
 * NC_EDIT_ERROPT_STOP - Following callback after failure are not executed, all successful callbacks executed till
                         failure point must be applied to the device.
 * NC_EDIT_ERROPT_CONT - Failed callbacks are skipped, but all callbacks needed to apply configuration changes are executed
 * NC_EDIT_ERROPT_ROLLBACK - After failure, following callbacks are not executed, but previous successful callbacks are
                         executed again with previous configuration data to roll it back.
 */
NC_EDIT_ERROPT_TYPE erropt = NC_EDIT_ERROPT_NOTSET;

/**
 * @brief Initialize plugin after loaded and before any other functions are called.

 * This function should not apply any configuration data to the controlled device. If no
 * running is returned (it stays *NULL), complete startup configuration is consequently
 * applied via module callbacks. When a running configuration is returned, libnetconf
 * then applies (via module's callbacks) only the startup configuration data that
 * differ from the returned running configuration data.

 * Please note, that copying startup data to the running is performed only after the
 * libnetconf's system-wide close - see nc_close() function documentation for more
 * information.

 * @param[out] running	Current configuration of managed device.

 * @return EXIT_SUCCESS or EXIT_FAILURE
 */
int transapi_init(xmlDocPtr *running) {
	//write_on_file("/home/pietro/repo/qameleon/netconf_server_netopeer/file.txt", "Init tpa");
	return EXIT_SUCCESS;
}

/**
 * @brief Free all resources allocated on plugin runtime and prepare plugin for removal.
 */
void transapi_close(void) {
	//write_on_file("/home/pietro/repo/qameleon/netconf_server_netopeer/file.txt", "Closing tpa");
	return;
}

/**
 * @brief Retrieve state data from device and return them as XML document
 *
 * @param model	Device data model. libxml2 xmlDocPtr.
 * @param running	Running datastore content. libxml2 xmlDocPtr.
 * @param[out] err  Double pointer to error structure. Fill error when some occurs.
 * @return State data as libxml2 xmlDocPtr or NULL in case of error.
 */
xmlDocPtr get_state_data(xmlDocPtr model, xmlDocPtr running, struct nc_err **err) {
	//write_on_file("/home/pietro/repo/qameleon/netconf_server_netopeer/file.txt", "get_state_data tpa");
	return(NULL);
}
/*
 * Mapping prefixes with namespaces.
 * Do NOT modify this structure!
 */
struct ns_pair namespace_mapping[] = {{"tpa", "http://org/nextworks/qameleon/tpa"}, {NULL, NULL}};

/*
 * CONFIGURATION callbacks
 * Here follows set of callback functions run every time some change in associated part of running datastore occurs.
 * You can safely modify the bodies of all function as well as add new functions for better lucidity of code.
 */

/**
 * @brief This callback will be run when node in path /tpa:tpa/tpa:tpa-config/tpa:tpa-id changes
 *
 * @param[in] data	Double pointer to void. Its passed to every callback. You can share data using it.
 * @param[in] op	Observed change in path. XMLDIFF_OP type.
 * @param[in] old_node	Old configuration node. If op == XMLDIFF_ADD, it is NULL.
 * @param[in] new_node	New configuration node. if op == XMLDIFF_REM, it is NULL.
 * @param[out] error	If callback fails, it can return libnetconf error structure with a failure description.
 *
 * @return EXIT_SUCCESS or EXIT_FAILURE
 */
/* !DO NOT ALTER FUNCTION SIGNATURE! */
int callback_tpa_tpa_tpa_tpa_config_tpa_tpa_id(void **data, XMLDIFF_OP op, xmlNodePtr old_node, xmlNodePtr new_node, struct nc_err **error) {
	//write_on_file("/home/pietro/repo/qameleon/netconf_server_netopeer/file.txt", "callback_tpa_tpa_tpa_tpa_config_tpa_tpa_id");
	return EXIT_SUCCESS;
}

char* xml_node_to_string(xmlNodePtr xml_node_ptr){
	if(xml_node_ptr==NULL){
		return "is NULL, nothing to show";	
	}
	xmlChar *xmlbuff;
	int buffersize;
	xmlDocPtr doc=xml_node_ptr->doc;
	xmlDocDumpFormatMemory(doc, &xmlbuff, &buffersize, 1);
	return xmlbuff;
}

/**
 * @brief This callback will be run when node in path /tpa:tpa changes
 *
 * @param[in] data	Double pointer to void. Its passed to every callback. You can share data using it.
 * @param[in] op	Observed change in path. XMLDIFF_OP type.
 * @param[in] old_node	Old configuration node. If op == XMLDIFF_ADD, it is NULL.
 * @param[in] new_node	New configuration node. if op == XMLDIFF_REM, it is NULL.
 * @param[out] error	If callback fails, it can return libnetconf error structure with a failure description.
 *
 * @return EXIT_SUCCESS or EXIT_FAILURE
 */
/* !DO NOT ALTER FUNCTION SIGNATURE! */
int callback_tpa_tpa(void **data, XMLDIFF_OP op, xmlNodePtr old_node, xmlNodePtr new_node, struct nc_err **error) {
	printf("Raw content NEW is :%s\n",(char*)xml_node_to_string(new_node));
	send_string_over_socket((char*)xml_node_to_string(new_node));
	printf("Raw content OLD is :%s\n",(char*)xml_node_to_string(old_node));
	return EXIT_SUCCESS;
}

/*
 * Structure transapi_config_callbacks provide mapping between callback and path in configuration datastore.
 * It is used by libnetconf library to decide which callbacks will be run.
 * DO NOT alter this structure
 */
struct transapi_data_callbacks clbks =  {
	.callbacks_count = 2,
	.data = NULL,
	.callbacks = {
		{.path = "/tpa:tpa/tpa:tpa-config/tpa:tpa-id", .func = callback_tpa_tpa_tpa_tpa_config_tpa_tpa_id},
		{.path = "/tpa:tpa", .func = callback_tpa_tpa}
	}
};

/**
 * @brief Get a node from the RPC input. The first found node is returned, so if traversing lists,
 * call repeatedly with result->next as the node argument.
 *
 * @param name	Name of the node to be retrieved.
 * @param node	List of nodes that will be searched.
 * @return Pointer to the matching node or NULL
 */
xmlNodePtr get_rpc_node(const char *name, const xmlNodePtr node) {
	xmlNodePtr ret = NULL;
	//write_on_file("get_rpc_node");
	for (ret = node; ret != NULL; ret = ret->next) {
		if (xmlStrEqual(BAD_CAST name, ret->name)) {
			break;
		}
	}

	return ret;
}

/*
 * RPC callbacks
 * Here follows set of callback functions run every time RPC specific for this device arrives.
 * You can safely modify the bodies of all function as well as add new functions for better lucidity of code.
 * Every function takes an libxml2 list of inputs as an argument.
 * If input was not set in RPC message argument is set to NULL. To retrieve each argument, preferably use get_rpc_node().
 */

/*
 * Structure transapi_rpc_callbacks provides mapping between callbacks and RPC messages.
 * It is used by libnetconf library to decide which callbacks will be run when RPC arrives.
 * DO NOT alter this structure
 */
struct transapi_rpc_callbacks rpc_clbks = {
	.callbacks_count = 0,
	.callbacks = {
	}
};

/*
 * Structure transapi_file_callbacks provides mapping between specific files
 * (e.g. configuration file in /etc/) and the callback function executed when
 * the file is modified.
 * The structure is empty by default. Add items, as in example, as you need.
 *
 * Example:
 * int example_callback(const char *filepath, xmlDocPtr *edit_config, int *exec) {
 *     // do the job with changed file content
 *     // if needed, set edit_config parameter to the edit-config data to be applied
 *     // if needed, set exec to 1 to perform consequent transapi callbacks
 *     return 0;
 * }
 *
 * struct transapi_file_callbacks file_clbks = {
 *     .callbacks_count = 1,
 *     .callbacks = {
 *         {.path = "/etc/my_cfg_file", .func = example_callback}
 *     }
 * }
 */
struct transapi_file_callbacks file_clbks = {
	.callbacks_count = 0,
	.callbacks = {{NULL}}
};

