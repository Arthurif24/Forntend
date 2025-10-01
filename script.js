let clientes = [];

function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.classList.remove('active');
    });

    const menuBtns = document.querySelectorAll('.menu-btn');
    menuBtns.forEach(btn => {
        btn.classList.remove('active');
    });

    document.getElementById(sectionId).classList.add('active');
    event.target.classList.add('active');

    if (sectionId === 'listar') {
        listarClientes();
    }
}

function cadastrarCliente(event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const telefone = document.getElementById('telefone').value;
    const endereco = document.getElementById('endereco').value;

    const cliente = {
        nome: nome,
        telefone: telefone,
        endereco: endereco
    };

    clientes.push(cliente);

    const mensagem = document.getElementById('mensagemCadastro');
    mensagem.textContent = 'Cliente cadastrado com sucesso!';
    mensagem.className = 'mensagem sucesso';

    document.getElementById('formCadastro').reset();

    setTimeout(() => {
        mensagem.style.display = 'none';
    }, 3000);
}

function listarClientes() {
    const lista = document.getElementById('listaClientes');

    if (clientes.length === 0) {
        lista.innerHTML = '<p class="vazio">Nenhum cliente cadastrado.</p>';
        return;
    }

    lista.innerHTML = '';

    clientes.forEach((cliente, index) => {
        const card = document.createElement('div');
        card.className = 'cliente-card';
        card.innerHTML = `
            <div class="cliente-info">
                <p><strong>Nome:</strong> ${cliente.nome}</p>
                <p><strong>Telefone:</strong> ${cliente.telefone}</p>
                <p><strong>Endereço:</strong> ${cliente.endereco}</p>
            </div>
            <div class="cliente-acoes">
                <button class="btn btn-editar" onclick="abrirModalEditar(${index})">Editar</button>
                <button class="btn btn-remover" onclick="removerCliente(${index})">Remover</button>
            </div>
        `;
        lista.appendChild(card);
    });
}

function buscarCliente() {
    const nomeBusca = document.getElementById('buscarNome').value.toLowerCase();
    const resultado = document.getElementById('resultadoBusca');

    if (nomeBusca === '') {
        resultado.innerHTML = '<p class="vazio">Digite um nome para buscar.</p>';
        return;
    }

    const clientesEncontrados = clientes.filter(cliente =>
        cliente.nome.toLowerCase().includes(nomeBusca)
    );

    if (clientesEncontrados.length === 0) {
        resultado.innerHTML = '<p class="vazio">Cliente não encontrado.</p>';
        return;
    }

    resultado.innerHTML = '';

    clientesEncontrados.forEach((cliente, index) => {
        const card = document.createElement('div');
        card.className = 'cliente-card';
        card.innerHTML = `
            <div class="cliente-info">
                <p><strong>Nome:</strong> ${cliente.nome}</p>
                <p><strong>Telefone:</strong> ${cliente.telefone}</p>
                <p><strong>Endereço:</strong> ${cliente.endereco}</p>
            </div>
        `;
        resultado.appendChild(card);
    });
}

function abrirModalEditar(index) {
    const modal = document.getElementById('modalEditar');
    const cliente = clientes[index];

    document.getElementById('editarIndex').value = index;
    document.getElementById('editarNome').value = cliente.nome;
    document.getElementById('editarTelefone').value = cliente.telefone;
    document.getElementById('editarEndereco').value = cliente.endereco;

    modal.classList.add('active');
}

function fecharModal() {
    const modal = document.getElementById('modalEditar');
    modal.classList.remove('active');
}

function salvarEdicao(event) {
    event.preventDefault();

    const index = document.getElementById('editarIndex').value;
    const nome = document.getElementById('editarNome').value;
    const telefone = document.getElementById('editarTelefone').value;
    const endereco = document.getElementById('editarEndereco').value;

    clientes[index] = {
        nome: nome,
        telefone: telefone,
        endereco: endereco
    };

    fecharModal();
    listarClientes();
}

function removerCliente(index) {
    if (confirm('Tem certeza que deseja remover este cliente?')) {
        clientes.splice(index, 1);
        listarClientes();
    }
}

window.onclick = function(event) {
    const modal = document.getElementById('modalEditar');
    if (event.target === modal) {
        fecharModal();
    }
}
