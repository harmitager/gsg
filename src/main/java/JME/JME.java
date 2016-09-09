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

/** Sample 1 - how to get started with the most simple JME 3 application.
 * Display a blue 3D cube and view from all sides by
 * moving the mouse and pressing the WASD keys. */
public class JME extends SimpleApplication {

    public static void main(String[] args){
        JME app = new JME();
        app.start(); // start the game
    }

    @Override
    public void simpleInitApp() {
        Spatial map = assetManager.loadModel("Models/map.obj");
        Spatial v1 = ((Node)map).getChild(0);
        Spatial v2 = ((Node)map).getChild(2);
        Spatial v3 = ((Node)map).getChild(4);
        Material mat_brick = new Material(
                assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat_brick.setTexture("ColorMap",
                assetManager.loadTexture("Textures/test.jpg"));
        v1.setMaterial(mat_brick);
        v1.scale(5f, 5f, 5f);
        v1.setLocalTranslation(2.0f,-2.5f,0.0f);
        v1.rotate(-3.0f, -90.0f, 0.0f);
        v2.setMaterial(mat_brick);
        v2.scale(5f, 5f, 5f);
        v2.setLocalTranslation(2.0f,-2.5f,0.0f);
        v2.rotate(-3.0f, -90.0f, 0.0f);
        v3.setMaterial(mat_brick);
        v3.scale(5f, 5f, 5f);
        v3.setLocalTranslation(2.0f,-2.5f,0.0f);
        v3.rotate(-3.0f, -90.0f, 0.0f);
        rootNode.attachChild(map);

        // You must add a light to make the model visible
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
        rootNode.addLight(sun);
        /*
        Box b = new Box(1, 1, 1); // create cube shape
        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        geom.setMaterial(mat);                   // set the cube's material
        rootNode.attachChild(geom);              // make the cube appear in the scene*/
    }
}