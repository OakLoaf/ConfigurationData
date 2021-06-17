package me.xemor.configurationdata;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;

public class ItemStackData {

    private ItemStack item;

    public ItemStackData(ConfigurationSection configurationSection, String defaultMaterial) {
        init(configurationSection, defaultMaterial);
    }

    public ItemStackData(ConfigurationSection configurationSection) {
        init(configurationSection, "STONE");
    }

    public void init(ConfigurationSection configurationSection, String defaultMaterial) {
        Material material = Material.valueOf(configurationSection.getString("type", defaultMaterial).toUpperCase());
        int amount = configurationSection.getInt("amount", 1);
        item = new ItemStack(material, amount);
        ConfigurationSection metadataSection = configurationSection.getConfigurationSection("metadata");
        if (metadataSection != null) {
            ItemMeta meta = Bukkit.getItemFactory().getItemMeta(material);
            if (meta != null) {
                ItemMetaData itemMetaData;
                if (meta instanceof PotionMeta) itemMetaData = new PotionMetaData(metadataSection, meta);
                else itemMetaData = new ItemMetaData(metadataSection, meta);
                item.setItemMeta(itemMetaData.getItemMeta());
            }
        }
    }

    public boolean isEqual(ItemStack item) {
        return item.getType() == this.item.getType() && item.hasItemMeta() == this.item.hasItemMeta() && (!item.hasItemMeta() || Bukkit.getItemFactory().equals(item.getItemMeta(), this.item.getItemMeta()));
    }

    public ItemStack getItem()
    {
        return item;
    }

}
