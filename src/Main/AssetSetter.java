package Main;

import Object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setAssets(){
        gp.superObject[0] = new OBJ_Key();
        gp.superObject[0].worldX = 15*gp.tilesSize;
        gp.superObject[0].worldY = 3*gp.tilesSize;

    }
}
