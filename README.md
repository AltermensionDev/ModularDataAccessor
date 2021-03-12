# Bukkit PersistentDataHolder
如果你不知道甚麼是PersistentDataHolder的話，你可以看看[這個](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/persistence/PersistentDataHolder.html)。

# PersistentDataAccessor
假設我們有一個POJO需要儲存進物品(只要是PersistentDataHolder就行)
```Java
public class SkillData {
	int damage;

	public int getDamage() { 
		return damage; 
	}

	public void setDamage(int damage) { 
		this.damage = damage;
	}
}
```
ModularData採用Gson進行序列化，以上只是個範例。

我們主要會用到的是:
```Java
PersistentDataAccessor<T> accessor = new PersistentDataAccessor<T>(PersistentDataHolder, Class<T>);
```

在上面的案例，如果我們想把`SkillData`存放到物品中，可以這樣做:
```Java
ItemStack item = new ItemStack(Material.DIAMOND_SWORD);

PersistentDataAccessor<SkillData> accessor = new PersistentDataAccessor<SkillData>(item.getItemMeta(), SkillData.class); // 不用特地創建ItemMeta也行
accessor.createIfNotExist(SkillData::new);
System.out.println(accessor.get().damage);
```
