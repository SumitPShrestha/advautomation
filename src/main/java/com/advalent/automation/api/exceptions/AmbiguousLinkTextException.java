package com.advalent.automation.api.exceptions;

/**
 * Thrown when more than one links with the same linkText
 * is encountered
 *
 * @author sshrestha
 */
public class AmbiguousLinkTextException extends AutomationException {
    public AmbiguousLinkTextException(String linkText) {
        super("More than one links with the link text " + linkText + " was found");
    }
}
