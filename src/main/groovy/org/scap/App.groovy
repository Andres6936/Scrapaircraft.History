package org.scap

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.DomElement
import com.gargoylesoftware.htmlunit.html.HtmlDivision
import com.gargoylesoftware.htmlunit.html.HtmlPage

class Application {
    // Const Fields Static

    private final static WebClient webClient = new WebClient();

    private final static ArrayList<String> links = new ArrayList<>();

    // Methods

    static def run() {
        webClient.getOptions().setJavaScriptEnabled(false);
        final HtmlPage page = webClient.getPage("https://www.airliners.net/aircraft-data");
        final ArrayList<HtmlDivision> div = page.getByXPath("//div[@class='title']");

        for (HtmlDivision element : div) {
            // The first child element is 'a'
            DomElement linkElement = element.getFirstElementChild();
            // Extract the link to page with information
            links.add(linkElement.getAttribute('href'));
        }

        print links;
    }
}

new Application().run();