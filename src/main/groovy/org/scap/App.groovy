package org.scap

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.DomElement
import com.gargoylesoftware.htmlunit.html.HtmlDivision
import com.gargoylesoftware.htmlunit.html.HtmlElement
import com.gargoylesoftware.htmlunit.html.HtmlPage
import groovy.json.JsonOutput

class Application {
    // Const Fields Static

    private final static WebClient webClient = new WebClient();

    private final static ArrayList<String> links = new ArrayList<>();

    private final static ArrayList<Aircraft> aircrafts = new ArrayList<>();

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

            Aircraft aircraft = new Aircraft();

            aircraft.setName(name);

            final ArrayList<HtmlDivision> properties = page.getByXPath('//div[@class="aircraftProperty"]');

            for (HtmlDivision property : properties) {
                String nameDiv = property.getFirstByXPath('div[@class="name"]').asText();
                String descriptionDiv = property.getFirstByXPath('div[@class="description"]').asText();

                if (nameDiv == "Country of Origin") {
                    aircraft.setCountryOrigin(descriptionDiv);
                } else if (nameDiv == "Type") {
                    aircraft.setType(descriptionDiv)
                } else if (nameDiv == "History") {
                    aircraft.setHistory(descriptionDiv)
                } else if (nameDiv == "Powerplants") {
                    aircraft.setPowerPlants(descriptionDiv)
                } else if (nameDiv == "Performance") {
                    aircraft.setPerformance(descriptionDiv)
                } else if (nameDiv == "Weights") {
                    aircraft.setWeights(descriptionDiv)
                } else if (nameDiv == "Dimensions") {
                    aircraft.setDimensions(descriptionDiv)
                } else if (nameDiv == "Capacity") {
                    aircraft.setCapacity(descriptionDiv)
                } else if (nameDiv == "Production") {
                    aircraft.setProduction(descriptionDiv)
                } else if (nameDiv == "Related Links") {
                    aircraft.setRelatedAircraft(descriptionDiv)
                } else {
                    println "Unknown attribute: ${nameDiv} \n ${descriptionDiv}"
                }

                aircrafts.add(aircraft);
            }

            def json = JsonOutput.prettyPrint(JsonOutput.toJson(aircraft));

            println json;

            // Debug Break
            break;
        }
    }
}

new Application().run();