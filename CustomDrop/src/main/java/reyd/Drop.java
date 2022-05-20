package reyd;

import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.item.EntityItem;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.TaskHandler;

import java.util.*;

import static cn.nukkit.Server.getInstance;

public class Drop extends PluginBase {

    private static Drop instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        onTick();
        super.onEnable();
        this.getLogger().info("§fEnable: §aCustomDrop");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void onTick() {
        getInstance().getScheduler().scheduleRepeatingTask(instance, new Runnable() {
            @Override
            public void run() {
                drop();
            }
        }, 20, true);
    }

    public void drop() {

        Map<Integer, Level> levels = getInstance().getLevels();

        for (Map.Entry<Integer, Level> entry : levels.entrySet()) {
            int k = entry.getKey();
            Level v = entry.getValue();

            for (Entity entity : Server.getInstance().getLevel(k).getEntities()) {
                if (entity instanceof EntityItem) {

                    if (entity.getNameTag() == null) {

                        entity.setNameTag("30");
                        entity.setNameTagAlwaysVisible(true);
                        entity.setNameTagVisible(true);

                    }

                    if (entity.getNameTag() != null) {

                        if (entity.getNameTag().equals("")) {
                            entity.setNameTag("30");
                            entity.setNameTagAlwaysVisible(true);
                            entity.setNameTagVisible(true);
                        } else if (entity.getNameTag().equals("1")) {

                            entity.close();

                        } else {

                            String f = entity.getNameTag();
                            int n = Integer.parseInt(f); //
                            int d = n - 1;
                            entity.setNameTag(String.valueOf(d));
                            entity.setNameTagAlwaysVisible(true);
                            entity.setNameTagVisible(true);

                        }

                    }
                }
            }
        }
    }
}
