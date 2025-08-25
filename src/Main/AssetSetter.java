package Main;

import entity.NPC_OldMan;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_OpenDoor;
import object.OBJ_Boots;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setObject()
    {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 32 * gp.tileSize;
        gp.obj[0].worldY = 40 * gp.tileSize;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 14 * gp.tileSize;

        gp.obj[2] = new OBJ_Key();
        gp.obj[2].worldX = 39 * gp.tileSize;
        gp.obj[2].worldY = 41 * gp.tileSize;

        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 14 * gp.tileSize;
        gp.obj[3].worldY = 22 * gp.tileSize;

        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 15 * gp.tileSize;

        gp.obj[5] = new OBJ_Door();
        gp.obj[5].worldX = 11 * gp.tileSize;
        gp.obj[5].worldY = 11 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest();
        gp.obj[6].worldX = 11 * gp.tileSize;
        gp.obj[6].worldY = 9 * gp.tileSize;

        gp.obj[7] = new OBJ_Boots();
        gp.obj[7].worldX = 39 * gp.tileSize;
        gp.obj[7].worldY = 35 * gp.tileSize;
    }

    public void setNPC()
    {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;

        gp.npc[1] = new NPC_OldMan(gp);
        gp.npc[1].worldX = gp.tileSize*10;
        gp.npc[1].worldY = gp.tileSize*10;

        gp.npc[1] = new NPC_OldMan(gp);
        gp.npc[1].worldX = gp.tileSize*11;
        gp.npc[1].worldY = gp.tileSize*9;

        gp.npc[2] = new NPC_OldMan(gp);
        gp.npc[2].worldX = gp.tileSize*39;
        gp.npc[2].worldY = gp.tileSize*41;

        gp.npc[3] = new NPC_OldMan(gp);
        gp.npc[3].worldX = gp.tileSize*23;
        gp.npc[3].worldY = gp.tileSize*14;

        gp.npc[4] = new NPC_OldMan(gp);
        gp.npc[4].worldX = gp.tileSize*32;
        gp.npc[4].worldY = gp.tileSize*38;

        gp.npc[5] = new NPC_OldMan(gp);
        gp.npc[5].worldX = gp.tileSize*35;
        gp.npc[5].worldY = gp.tileSize*24;

        gp.npc[6] = new NPC_OldMan(gp);
        gp.npc[6].worldX = gp.tileSize*28;
        gp.npc[6].worldY = gp.tileSize*15;

        gp.npc[7] = new NPC_OldMan(gp);
        gp.npc[7].worldX = gp.tileSize*13;
        gp.npc[7].worldY = gp.tileSize*18;

        gp.npc[8] = new NPC_OldMan(gp);
        gp.npc[8].worldX = gp.tileSize*12;
        gp.npc[8].worldY = gp.tileSize*13;

        gp.npc[9] = new NPC_OldMan(gp);
        gp.npc[9].worldX = gp.tileSize*32;
        gp.npc[9].worldY = gp.tileSize*31;

        gp.npc[10] = new NPC_OldMan(gp);
        gp.npc[10].worldX = gp.tileSize*15;
        gp.npc[10].worldY = gp.tileSize*28;

        gp.npc[11] = new NPC_OldMan(gp);
        gp.npc[11].worldX = gp.tileSize*35;
        gp.npc[11].worldY = gp.tileSize*8;

        gp.npc[12] = new NPC_OldMan(gp);
        gp.npc[12].worldX = gp.tileSize*41;
        gp.npc[12].worldY = gp.tileSize*21;
    }

}
