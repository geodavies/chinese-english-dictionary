package uk.ac.aston.dc2310.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import uk.ac.aston.dc2310.formatter.OutputFormatter;

/**
 * @author George Davies
 * @since 18/05/17.
 */
public class ApplicationController {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationController.class);

    public void start(OutputFormatter outputFormatter) {
        LOGGER.debug("Application started with CLI");
    }

}
