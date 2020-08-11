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
        webClient.getOptions().setCssEnabled(false);
        final HtmlPage page = webClient.getPage("https://www.airliners.net/aircraft-data");
        final ArrayList<HtmlDivision> div = page.getByXPath("//div[@class='title']");

        for (HtmlDivision element : div) {
            // The first child element is 'a'
            DomElement linkElement = element.getFirstElementChild();
            // Extract the link to page with information
            links.add(linkElement.getAttribute('href'));
        }

        parseEachPage();
    }

    private static def parseEachPage() {
        for (String link : links) {
            final HtmlPage page = webClient.getPage('https://www.airliners.net' + link);

            // The first element is 'h1', it element content the name of actual aircraft
            String name = page.getFirstByXPath("//div[@class='layout-blue-panel']").getFirstElementChild().asText();

            // Debug Break
            break;
        }
    }
}

new Application().run();