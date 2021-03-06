package fr.elpresidente.game;

import fr.elpresidente.game.resources.resource.ResourcesController;
import junit.framework.TestCase;

public class ResourcesTest extends TestCase {
    ResourcesController resourcesController = ResourcesController.getInstance();

    public void testTheCumulativeOfRessourcesShouldBeEqualTo60WithAdding20AgricultureAnd40Industry() {
        resourcesController.addAgricultureSize(20);
        resourcesController.addIndustrySize(40);

        assertEquals(20, resourcesController.getAgriculture().getSize());
        assertEquals(40, resourcesController.getIndustry().getSize());
        assertEquals(60, resourcesController.getCumulativeResources());
    }

    public void testTheCumulativeOfRessourcesShouldBeEqualTo100EvenWithAdding80AgricultureAnd70Industry() {
        resourcesController = ResourcesController.resetInstance();
        resourcesController.addAgricultureSize(80);
        resourcesController.addIndustrySize(70);

        assertEquals(80, resourcesController.getAgriculture().getSize());
        assertEquals(20, resourcesController.getIndustry().getSize());
        assertEquals(100, resourcesController.getCumulativeResources());
    }

    public void testTheCumulativeOfRessourcesShouldBeEqualTo100EvenWithAdding200IndustryAndAgricultureStayAt0() {
        resourcesController = ResourcesController.resetInstance();
        resourcesController.addIndustrySize(200);
        resourcesController.addAgricultureSize(200);

        assertEquals(0, resourcesController.getAgriculture().getSize());
        assertEquals(100, resourcesController.getIndustry().getSize());
        assertEquals(100, resourcesController.getCumulativeResources());
    }
}
