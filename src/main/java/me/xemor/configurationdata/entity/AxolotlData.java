package me.xemor.configurationdata.entity;

import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Entity;

public class AxolotlData extends ExtraData {

    private Axolotl.Variant variant = null;

    public AxolotlData(ConfigurationSection configurationSection) {
        super(configurationSection);
        String variantStr = configurationSection.getString("variant");
        if (variantStr != null) {
            try {
                variant = Axolotl.Variant.valueOf(variantStr);
            } catch (IllegalArgumentException e) {
                ConfigurationData.getLogger().severe("You have entered an invalid variant at " + configurationSection.getCurrentPath() + ".variant");
            }
        }
    }

    @Override
    public void applyData(Entity entity) {
        if (entity instanceof Axolotl) {
            Axolotl axolotl = (Axolotl) entity;
            if (variant != null) {
                axolotl.setVariant(variant);
            }
        }
    }
}
