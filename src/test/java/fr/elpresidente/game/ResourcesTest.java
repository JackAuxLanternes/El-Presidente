package fr.elpresidente.game;

import fr.elpresidente.game.resources.ResourcesController;
import junit.framework.TestCase;

public class ResourcesTest extends TestCase{
    ResourcesController resourcesController = new ResourcesController();

    public void testTheCumulativeOfRessourcesShouldBeEqualTo60WithAdding20AgricultureAnd40Industry()
    {
        resourcesController.addAgriculture(20);
        resourcesController.addIndustry(40);

        assertEquals(20, resourcesController.getAgriculture().getSize());
        assertEquals(40, resourcesController.getIndustry().getSize());
        assertEquals(60, resourcesController.getCumulativeResources());
    }

    public void testTheCumulativeOfRessourcesShouldBeEqualTo100EvenWithAdding80AgricultureAnd70Industry()
    {
        resourcesController.addAgriculture(80);
        resourcesController.addIndustry(70);

        assertEquals(80, resourcesController.getAgriculture().getSize());
        assertEquals(20, resourcesController.getIndustry().getSize());
        assertEquals(100, resourcesController.getCumulativeResources());
    }

    public void testTheCumulativeOfRessourcesShouldBeEqualTo100EvenWithAdding200IndustryAndAgricultureStayAt0()
    {
        resourcesController.addIndustry(200);
        resourcesController.addAgriculture(200);

        assertEquals(0, resourcesController.getAgriculture().getSize());
        assertEquals(100, resourcesController.getIndustry().getSize());
        assertEquals(100, resourcesController.getCumulativeResources());
    }
}
