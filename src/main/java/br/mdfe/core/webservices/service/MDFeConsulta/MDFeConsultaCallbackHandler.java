
/**
 * MDFeConsultaCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package br.mdfe.core.webservices.service.MDFeConsulta;

    /**
     *  MDFeConsultaCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MDFeConsultaCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MDFeConsultaCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MDFeConsultaCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for mdfeConsultaMDF method
            * override this method for handling normal response from mdfeConsultaMDF operation
            */
           public void receiveResultmdfeConsultaMDF(
                    br.mdfe.core.webservices.service.MDFeConsulta.MDFeConsultaStub.MdfeConsultaMDFResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from mdfeConsultaMDF operation
           */
            public void receiveErrormdfeConsultaMDF(java.lang.Exception e) {
            }
                


    }
    