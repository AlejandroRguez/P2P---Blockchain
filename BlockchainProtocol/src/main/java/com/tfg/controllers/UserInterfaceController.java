package com.tfg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tfg.services.Services;

@Controller	
public class UserInterfaceController {
	
	/**
	 * Recibe el nombre un minero, devuelve los bloques asociados al mismo
	 * @param model
	 * @param miner El nombre del minero
	 * @return La vista blocks
	 */
	@RequestMapping(value = "/block/miner/{miner}")
	public String getMinedBlocks(Model model, @PathVariable String miner) {
		model.addAttribute("blockList", Services.getBlockService().getByMiner(miner));
		return "blocks";
	}
	
	/**
	 * Recibe el índice de bloque y obtiene la información asociada al mismo
	 * @param model
	 * @param index El índice del bloque
	 * @return La vista blockInfo
	 */
	@RequestMapping(value = "/block/info/{index}")
	public String getBlockByIndex(Model model, @PathVariable int index) {
		model.addAttribute("block", Services.getBlockService().getByIndex(index));
		return "blockInfo";
	}
	
	/**
	 * Construye el objeto simplificado que utilizamos para la interfaz web
	 * @param model
	 * @return La vista index
	 */
	@RequestMapping(value = "/")
	public String getBlockchainInfo(Model model) {
		model.addAttribute("blockchain", Services.getBlockchainService().getInfo());
		return "index";
	}
	
	/**
	 * Obtiene todos los bloques de la cadena
	 * @param model
	 * @return La vista totalBlocks
	 */
	@RequestMapping(value = "/blocks") 
	public String getBlocks(Model model) {
		model.addAttribute("blockList", Services.getBlockchainService().getInfo().getBlocks());
		return "totalBlocks";
	}

}
