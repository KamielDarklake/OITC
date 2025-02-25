package me.flame.oitc.players.shop.gui;

import me.flame.oitc.players.User;
import me.flame.oitc.players.shop.Shop;
import me.flame.oitc.players.shop.manager.ShopManager;
import me.flame.oitc.utils.ChatUtils;
import me.flame.oitc.utils.FileManager;
import me.flame.oitc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.List;

public class ShopGUI {

    ShopManager shopManager = new ShopManager();

    public Inventory openShopGUI(User user) {

        Player p = Bukkit.getServer().getPlayer(user.getUuid());
        Inventory inventory = Bukkit.createInventory(null, 27, ChatUtils.format("&7Shop: &a" + user.getName()));

        Integer cooldown = FileManager.get("config.yml").getInt("rewards.arrow-timer");
        Integer cooldownReduce = FileManager.get("config.yml").getInt("rewards.cooldown-reduce");

        inventory.setItem(10, new ItemBuilder(Material.EMERALD, 1).setDisplayName("&aColor Shop &c(SOON)")
                .setLore(""
                        , "&7&oHere you can buy new colors for your armor."
                        , "&7&oThis feature is coming soon.").setEnchanted().setItemFlag(ItemFlag.HIDE_ENCHANTS).build());

        inventory.setItem(12, new ItemBuilder(Material.ARROW, 1).setDisplayName("&aArrow Timer")
                .setLore(""
                        , "&f Current level: &a" + (user.getArrowLevel() >= 5 ? "&cMax Level" : user.getArrowLevel())
                        , "&f Current cooldown: &a" + (cooldown - (cooldownReduce * user.getArrowLevel())) + " &7(-" + cooldownReduce * user.getArrowLevel() + " seconds)"
                        , ""
                        , "&f Upgrade cost: &a" + (user.getArrowLevel() >= 5 ? "&c0" : shopManager.getCost(ShopManager.getShop("arrow"), (user.getArrowLevel() + 1)) + " &7Coins.")
                        , ""
                        , "&7&oUpgrading your Arrow Timer will give your a"
                        , "&7&ocooldown reduction of &f&o15 seconds&7&o.").build());

        inventory.setItem(14, new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setDisplayName("&aArmor").setLore(armorLore(user))
                .setItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

        inventory.setItem(16, new ItemBuilder(Material.DIAMOND_SWORD, 1).setDisplayName("&aSword").setLore(swordLore(user))
                .setItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());

        inventory.setContents(inventory.getContents());
        p.openInventory(inventory);

        return inventory;
    }

    public List<String> armorLore(User user){
        List<String> list = new ArrayList();
        list.add(ChatUtils.format(""));
        list.add(ChatUtils.format("&f Current level: &a" + (user.getArmorLevel() >= 5 ? "&cMax Level" : user.getArmorLevel())));
        list.add(ChatUtils.format("&f Upgrade cost: &a" + (user.getArmorLevel() >= 5 ? "&c0" : shopManager.getCost(ShopManager.getShop("armor"), (user.getArmorLevel() + 1)) + " &7Coins.")));
        list.add("");
        list.add(ChatUtils.format("&f Upgraded Armor:"));
        switch (user.getArmorLevel() + 1){
            case 0:
            default:
                list.add(ChatUtils.format("&7 × &fHelmet: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fLeggings: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fBoots: &aProtection 0"));

                break;
            case 1:
                list.add(ChatUtils.format("&7 × &fHelmet: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fLeggings: &aProtection 0"));
                list.add(ChatUtils.format("&7 × &fBoots: &aProtection 1"));

                break;
            case 2:
                list.add(ChatUtils.format("&7 × &fHelmet: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fLeggings: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fBoots: &aProtection 1"));

                break;
            case 3:
                list.add(ChatUtils.format("&7 × &fHelmet: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fLeggings: &aProtection 1"));
                list.add(ChatUtils.format("&7 × &fBoots: &aProtection 2"));

                break;
            case 4:
                list.add(ChatUtils.format("&7 × &fHelmet: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fLeggings: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fBoots: &aProtection 2"));

                break;
            case 5:
                list.add(ChatUtils.format("&7 × &fHelmet: &aProtection 3"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fLeggings: &aProtection 2"));
                list.add(ChatUtils.format("&7 × &fBoots: &aProtection 3"));

                break;
            case 6:
                list.add(ChatUtils.format("&7 × &fHelmet: &aProtection 3"));
                list.add(ChatUtils.format("&7 × &fChestplate: &aProtection 3"));
                list.add(ChatUtils.format("&7 × &fLeggings: &aProtection 3"));
                list.add(ChatUtils.format("&7 × &fBoots: &aProtection 3"));

                break;

        }
        list.add(ChatUtils.format(""));
        list.add(ChatUtils.format("&7&oUpgrading your armor will give your more"));
        list.add(ChatUtils.format("&7&oenchantments on your armor."));

        return list;
    }

    public List<String> swordLore(User user){
        List<String> list = new ArrayList();
        list.add(ChatUtils.format(""));
        list.add(ChatUtils.format("&f Current level: &a" + (user.getSwordLevel() >= 5 ? "&cMax Level" : user.getSwordLevel())));
        list.add(ChatUtils.format("&f Upgrade cost: &a" + (user.getSwordLevel() >= 5 ? "&c0" : shopManager.getCost(ShopManager.getShop("sword"), (user.getSwordLevel() + 1)) + " &7Coins.")));
        list.add("");
        list.add(ChatUtils.format("&f Upgraded Sword:"));
        switch (user.getSwordLevel() + 1){
            case 0:
            default:
                list.add(ChatUtils.format("&7 × &fType: &aStone Sword"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aNone"));

                break;
            case 1:
                list.add(ChatUtils.format("&7 × &fType: &aStone Sword"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aSharpness 1"));

                break;
            case 2:
                list.add(ChatUtils.format("&7 × &fType: &aStone Sword"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aSharpness 2"));

                break;
            case 3:
                list.add(ChatUtils.format("&7 × &fType: &aIron Sword"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aNone"));

                break;
            case 4:
                list.add(ChatUtils.format("&7 × &fType: &aIron Sword"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aSharpness 1"));

                break;
            case 5:
                list.add(ChatUtils.format("&7 × &fType: &aIron Sword"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aSharpness 2"));

                break;
            case 6:
                list.add(ChatUtils.format("&7 × &fType: &aDiamond Sword"));
                list.add(ChatUtils.format("&7 × &fEnchantment: &aNone"));

                break;

        }
        list.add(ChatUtils.format(""));
        list.add(ChatUtils.format("&7&oUpgrading your sword will give you better"));
        list.add(ChatUtils.format("&7&oenchantments and a better sword."));

        return list;
    }
}
