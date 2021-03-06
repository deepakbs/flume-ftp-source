/*****************************************************************
* FptSourceCounter exposes custom metrics for specific source FTP
************/

package org.apache.flume.source;

import org.apache.flume.instrumentation.MonitoredCounterGroup;
/**
 * 
 * @author Luis Lázaro <lalazaro@keedio.com>
 */
public class FtpSourceCounter extends MonitoredCounterGroup implements FtpSourceCounterMBean {
    
    private static  long files_count                        /* contador de ficheros descubiertos */
                                , filesProcCount                   /* contador de ficheros descubiertos y procesados con éxito */
                                , filesProcCountError            /* contador de ficheros descubiertos y procesados con error */
                                , eventCount                        /* contador de eventos */
                                , sendThroughput                /* tasa de eventos por segundo */
                                , start_time                          /* milisegundos desde EPOC hasta creación de contador */
                                , last_sent                            /* milisegundos desde EPOC hasta generación de último evento */
                                , countModProc                    /* contador de modificaciones que se han procesado con éxito */ 
                                , mbProcessed                     /* megabytes de datos procesados*/
                                , kbProcessed;                     /* kbytes de datos procesados*/
    
    private static  final String[] ATTRIBUTES = { "files_count" , "filesProcCount", "filesProcCountError", "eventCount","start_time","last_sent", 
                                                                        "sendThroughput", "countModProc", "mbProcessed", "kbProcessed"};                 
        
    public FtpSourceCounter(String name){
       super(MonitoredCounterGroup.Type.SOURCE, name, ATTRIBUTES);
       files_count = 0;
       filesProcCount = 0;
       filesProcCountError = 0;
       eventCount = 0;
       last_sent = 0;
       start_time = System.currentTimeMillis();
       sendThroughput = 0;
       countModProc = 0;
       mbProcessed = 0;
       kbProcessed = 0;
    }
            
    /*
    @return long, number of files discovered
    */
    @Override
    public long getFilesCount(){
        return files_count;
    }
    
    /*
    @void, increment count of files
    */
    @Override
    public void  incrementFilesCount(){
        files_count++;
    }
    
    /*
    @return long, files succesfully
    */
    @Override
    public long getFilesProcCount(){
        return filesProcCount;
    }
    
    /*
    @void, increment count of proc files    
    */
    @Override
    public void incrementFilesProcCount(){
        filesProcCount++;
    }
    
     /*
    @return long, files proc with error
    */
    @Override
    public long getFilesProcCountError(){
        return filesProcCountError;
    }
    
    /*
    @void, increment count of proc files with error    
    */
    @Override
    public void incrementFilesProcCountError(){
        filesProcCountError++;
    }
    
    @Override
    public void incrementEventCount(){
        last_sent = System.currentTimeMillis();
        eventCount++;
       if (last_sent - start_time >= 1000) {
           long secondsElapsed = (last_sent - start_time) / 1000;
           sendThroughput = eventCount / secondsElapsed;
        }
    }
    
    @Override
    public long getEventCount(){
        return eventCount;
    }
    
    @Override
    public long getSendThroughput() {
        return sendThroughput;
    }
    
    @Override
    public void incrementCountModProc(){
        countModProc++;
    }
    
    @Override
    public long getCountModProc(){
        return countModProc;
    }
    
    @Override
    public long getMbProcessed(){
        mbProcessed = getEventCount() /(1024);
        return mbProcessed;
    }
    
    @Override
    public long getKbProcessed(){
        kbProcessed = getEventCount();
        return kbProcessed;
    }
    
    @Override
    public long getLastSent(){
        return last_sent;
    }
}
