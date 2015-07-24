package com.serli.selenium.concordion;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public class ResourceConcordionExtension implements ConcordionExtension {

    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withLinkedJavaScript("/scripts/jquery-1.9.0.min.js", new Resource("/scripts/jquery-1.9.0.min.js"));
        concordionExtender.withLinkedJavaScript("/scripts/Markdown.ClassConvert.js", new Resource("/scripts/Markdown.ClassConvert.js"));
        concordionExtender.withLinkedJavaScript("/scripts/Markdown.Converter.js", new Resource("/scripts/Markdown.Converter.js"));
    }
}