package br.com.sasoriengine.controlegarrafao.util;


import br.com.sasoriengine.controlegarrafao.model.Cliente;
import br.com.sasoriengine.controlegarrafao.model.ClienteDTO;
import br.com.sasoriengine.controlegarrafao.model.ClienteGarrafao;
import br.com.sasoriengine.controlegarrafao.model.ClienteGarrafaoDTO;
import br.com.sasoriengine.controlegarrafao.model.Garrafao;
import br.com.sasoriengine.controlegarrafao.model.GarrafaoDTO;

public class MapperDTO {

	public static ClienteDTO mapperCLiente(ClienteDTO clienteDTO, Cliente cliente) {

		clienteDTO.setClienteNome(cliente.getClienteNome());
		clienteDTO.setClienteId(cliente.getClienteId());
		clienteDTO.setClienteNumero(cliente.getClienteNumero());
		clienteDTO.setClienteRua(cliente.getClienteRua());
		clienteDTO.setClienteComplemento(cliente.getClienteComplemento());

		for (ClienteGarrafao clienteGarrafao : cliente.getClienteGarrafaos()) {
			ClienteGarrafaoDTO clienteGarrafaoDTO = new ClienteGarrafaoDTO();

			clienteGarrafaoDTO = mapperClienteForGarrafao(clienteGarrafaoDTO, clienteGarrafao);

			clienteDTO.getClienteGarrafaos().add(clienteGarrafaoDTO);
		}

		return clienteDTO;
	}

	public static ClienteGarrafaoDTO mapperClienteForGarrafao(ClienteGarrafaoDTO clienteGarrafaoDTO,
			ClienteGarrafao clienteGarrafao) {

		clienteGarrafaoDTO.setGarrafaoDTO(new GarrafaoDTO());
		clienteGarrafaoDTO.setQuantidade(clienteGarrafao.getQuantidade());
		clienteGarrafaoDTO.getGarrafaoDTO().setGarrafaoNome(clienteGarrafao.getGarrafao().getGarrafaoNome());
		clienteGarrafaoDTO.getGarrafaoDTO().setGarrafaoId(clienteGarrafao.getGarrafao().getGarrafaoId());

		return clienteGarrafaoDTO;
	}

	public static GarrafaoDTO mapperGarrafao(GarrafaoDTO garrafaoDTO, Garrafao garrafao) {

		garrafaoDTO.setGarrafaoNome(garrafao.getGarrafaoNome());
		garrafaoDTO.setGarrafaoId(garrafao.getGarrafaoId());
		
		for (ClienteGarrafao clienteGarrafao : garrafao.getClienteGarrafaos()) {
			ClienteGarrafaoDTO clienteGarrafaoDTO = new ClienteGarrafaoDTO();

			clienteGarrafaoDTO = mapperGarrafaoForCliente(clienteGarrafaoDTO, clienteGarrafao);

			garrafaoDTO.getClienteGarrafaos().add(clienteGarrafaoDTO);
		}

		return garrafaoDTO;
	}

	public static ClienteGarrafaoDTO mapperGarrafaoForCliente(ClienteGarrafaoDTO clienteGarrafaoDTO,
			ClienteGarrafao clienteGarrafao) {

		clienteGarrafaoDTO.setClienteDTO(new ClienteDTO());
		clienteGarrafaoDTO.getClienteDTO().setClienteId(clienteGarrafao.getCliente().getClienteId());
		clienteGarrafaoDTO.getClienteDTO().setClienteNome(clienteGarrafao.getCliente().getClienteNome());
		clienteGarrafaoDTO.getClienteDTO().setClienteRua(clienteGarrafao.getCliente().getClienteRua());
		clienteGarrafaoDTO.getClienteDTO().setClienteNumero(clienteGarrafao.getCliente().getClienteNumero());
		clienteGarrafaoDTO.getClienteDTO().setClienteComplemento(clienteGarrafao.getCliente().getClienteComplemento());
		clienteGarrafaoDTO.setQuantidade(clienteGarrafao.getQuantidade());

		return clienteGarrafaoDTO;
	}
}
