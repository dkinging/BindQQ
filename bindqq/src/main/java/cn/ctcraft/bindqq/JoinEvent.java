package cn.ctcraft.bindqq;

import cn.ctcraft.bindqq.database.Database;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    Database database;
    Bindqq bindqq;

    public JoinEvent(){
        bindqq = Bindqq.getPlugin(Bindqq.class);
        database = bindqq.database;
    }
    @EventHandler
    public void Join(PlayerJoinEvent e){
        Player player = e.getPlayer();
        String qq = database.getQQ(player.getUniqueId().toString());
        if(qq == null){
            int anInt = bindqq.getConfig().getInt("config.kick.time");
            player.sendMessage("§c§l请在"+anInt+"秒内输入/bqq [qq号]绑定qq,否则将被踢出服务器!");
            KickTimer kickTimer = new KickTimer(player);
            kickTimer.runTaskTimer(bindqq,anInt*20,anInt*20);
            TitleTimer titleTimer = new TitleTimer(player);
            anInt = bindqq.getConfig().getInt("config.title.time");
            titleTimer.runTaskTimer(bindqq,anInt*20,anInt*20);
        }
    }
}
