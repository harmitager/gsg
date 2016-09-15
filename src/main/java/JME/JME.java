package JME;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;
import country.Country;
import reader.Reader;
import world.World;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JME extends SimpleApplication {

    public static void main(String[] args) {
        JME app = new JME();

        app.showSettings = false;
        AppSettings appSettings = new AppSettings(true);
        appSettings.put("Width", 1240);
        appSettings.put("Height", 1024);
        appSettings.put("Title", "GSG");
        app.setSettings(appSettings);

        app.start(); // start the game
    }

    @Override
    public void simpleInitApp() {
        initMap();

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
    }

    public void initMap() {
        Node map = (Node) assetManager.loadModel("Models/map.obj");
        map.scale(5f, 5f, 5f);
        map.setLocalTranslation(2.0f, -2.5f, 0.0f);
        map.rotate(-3.0f, -90.0f, 0.0f);

        System.out.println(World.INSTANCE.send());

        int i = 0;
        for (Country country : World.INSTANCE.getCountries()) {
            Spatial current_province = ((Node) map).getChild(i);
            Material politMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            //Material politMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
            politMat.setColor("Color", new ColorRGBA());
            current_province.setMaterial(politMat);
        }

        rootNode.attachChild(map);
    }
}