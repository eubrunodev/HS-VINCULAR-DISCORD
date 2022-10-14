package me.eubrunodev.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.eubrunodev.managers.VincularManager;

public class StaffCommands extends VincularManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("�cComando so para jogadores");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("vinculador")) {
			if(args.length >=1) {
				if(args[0].equalsIgnoreCase("set")) {
					if(args.length < 2) {
						p.sendMessage(prefix+"�cUse: /vinculador set <jogador>");
						return true;
					}
					String target = String.valueOf(args[1]);
					if(staffGetCodigo(target) != null) {
						p.sendMessage("�cO jogador �d"+target+" �cj� possu� um c�digo!");
					} else {
						try {
						staffSetCodigo(target, gerar_key());
						p.sendMessage("�aNovo c�digo setado no jogador �d"+target+" �aNovo C�digo: �d"+staffGetCodigo(target));
						} catch (Exception e) {
						// TODO: handle exception
						}
					}
				} else if(args[0].equalsIgnoreCase("remove")) {
					if(args.length < 2) {
						p.sendMessage(prefix+"�cUse: /vinculador remove <jogador>");
						return true;
					}
					String target = String.valueOf(args[1]);
					if(staffGetCodigo(target) == null) {
						p.sendMessage("�cO jogador �d"+target+" �cn�o pussu� um c�digo!");
					} else {
						try {
							staffRemoveCodigo(target, staffGetCodigo(target));
							p.sendMessage("�aO jogador �d"+target+" �ateve seu �dc�digo �aremovido!");
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			}
			return false;
		}
		
		// /vinculador remove Sempaai  (Remove o codigo ja existente no banco de dados)
		// /vinculador set Sempaai (Seta um outro codigo random)
		return false;
	}

}
