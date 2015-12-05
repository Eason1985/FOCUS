package com.fc.focus.selenium.api.comman;

import java.util.List;

/**
 * Created by Eason on 15/12/5.
 */
public interface Request {

    /**
     * have one json per case.
     * @return
     */
    String toJSON();

    String getURL();

    String getEndpoint();


}
