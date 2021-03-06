/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.flume.source;
/**
 *
 * @author Luis Lázaro <lalazaro@keedio.com>
 */
public interface FtpSourceCounterMBean {
    public long getFilesCount();
    public void incrementFilesCount();
    public long getFilesProcCount();
    public void incrementFilesProcCount();
    public long getFilesProcCountError();
    public void incrementFilesProcCountError();
    public long getEventCount();
    public void incrementEventCount();
    public long getSendThroughput();
    public void incrementCountModProc();
    public long getCountModProc();
    public long getMbProcessed();
    public long getKbProcessed();
    public long getLastSent();
}