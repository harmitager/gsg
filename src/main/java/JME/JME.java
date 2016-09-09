package JME;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import com.jme3.system.AppSettings;

/** Sample 1 - how to get started with the most simple JME 3 application.
 * Display a blue 3D cube and view from all sides by
 * moving the mouse and pressing the WASD keys. */
public class JME extends SimpleApplication {

    public static void main(String[] args){
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
        Node map = (Node) assetManager.loadModel("Models/map.obj");
        Material mat_brick = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat_brick.setTexture("DiffuseMap", assetManager.loadTexture("Textures/123.jpg"));
        mat_brick.setTexture("NormalMap", assetManager.loadTexture("Textures/earth_normalmap.jpg"));
        map.setMaterial(mat_brick);
        map.scale(5f, 5f, 5f);
        map.setLocalTranslation(2.0f,-2.5f,0.0f);
        map.rotate(-3.0f, -90.0f, 0.0f);
        rootNode.attachChild(map);

        // You must add a light to make the model visible
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);

        /*for (int i=0; i<map.getQuantity(); i++){
            Spatial v1 = ((Node)map).getChild(i);
            Material mat2 = new Material(
                    assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat2.setColor("Color", ColorRGBA.Blue);
            v1.setMaterial(mat2);
        }
        */
    }
}