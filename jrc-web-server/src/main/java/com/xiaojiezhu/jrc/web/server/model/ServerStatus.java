package com.xiaojiezhu.jrc.web.server.model;

import java.io.Serializable;

/**
 * server info
 * @author xiaojie.zhu
 */
public class ServerStatus implements Serializable {

    /**
     * Config repository number
     */
    private int configNumber;

    /**
     * Jrc server number,not contains web server
     */
    private int serverNumber;

    /**
     * The client number of connection jrc servers
     */
    private int clientNumber;

    public ServerStatus() {
    }

    public ServerStatus(int configNumber, int serverNumber, int clientNumber) {
        this.configNumber = configNumber;
        this.serverNumber = serverNumber;
        this.clientNumber = clientNumber;
    }

    public int getConfigNumber() {
        return configNumber;
    }

    public void setConfigNumber(int configNumber) {
        this.configNumber = configNumber;
    }

    public int getServerNumber() {
        return serverNumber;
    }

    public void setServerNumber(int serverNumber) {
        this.serverNumber = serverNumber;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    @Override
    public String toString() {
        return "ServerStatus{" +
                "configNumber=" + configNumber +
                ", serverNumber=" + serverNumber +
                ", clientNumber=" + clientNumber +
                '}';
    }
}
