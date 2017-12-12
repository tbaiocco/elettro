
/**
 * CteRecepcaoEventoCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package br.cte.core.webservices.service.CTeEventos;

    /**
     *  CteRecepcaoEventoCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class CteRecepcaoEventoCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public CteRecepcaoEventoCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public CteRecepcaoEventoCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for cteRecepcaoEvento method
            * override this method for handling normal response from cteRecepcaoEvento operation
            */
           public void receiveResultcteRecepcaoEvento(
                    br.cte.core.webservices.service.CTeEventos.CteRecepcaoEventoStub.CteRecepcaoEventoResult result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cteRecepcaoEvento operation
           */
            public void receiveErrorcteRecepcaoEvento(java.lang.Exception e) {
            }
                


    }
    