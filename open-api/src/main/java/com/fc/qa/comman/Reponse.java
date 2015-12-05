package com.fc.qa.comman;

/**
 * Created by Eason on 15/12/5.
 */
public interface Reponse {

    Object getValue(String key);

    /**
     * http Code
     * @see
     * @return
     */
    int getHttpCode();


}
