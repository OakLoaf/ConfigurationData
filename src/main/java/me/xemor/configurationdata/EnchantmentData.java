package me.xemor.configurationdata;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class EnchantmentData {

    private HashMap<Enchantment, Integer> enchantments = new HashMap<>();

    public EnchantmentData(ConfigurationSection configurationSection) {
        for (Map.Entry<String, Object> entry :  configurationSection.getValues(false).entrySet()) {
            if (entry.getValue() instanceof Integer) {
                enchantments.put(Enchantment.getByKey(NamespacedKey.minecraft(entry.getKey().toLowerCase())), (Integer) entry.getValue());
            }
        }
    }

    public HashMap<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }

    public void applyEnchantments(ItemMeta itemMeta) {
        if (itemMeta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta) itemMeta;
            for (Map.Entry<Enchantment, Integer> item : enchantments.entrySet()) {
                enchantmentStorageMeta.addStoredEnchant(item.getKey(), item.getValue(), true);
            }
        }
        else {
            for (Map.Entry<Enchantment, Integer> item : enchantments.entrySet()) {
                itemMeta.addEnchant(item.getKey(), item.getValue(), true);
            }
        }

    }
}
