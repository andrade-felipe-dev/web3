<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listaItemsAluno" value="${sessionScope.listaItemsAluno}" />
<c:set var="listaItems" value="${sessionScope.listaItems}" />
<c:set var="alunoAtual" value="${sessionScope.alunoAtual}" />

<html>
<head>
    <c:import url="head.jsp" />
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var modals = document.querySelectorAll('.modal');
            M.Modal.init(modals);
        });
    </script>
</head>
<body>
    <nav>
        <div class="nav-wrapper light-blue darken-3">
            <a href="#" class="brand-logo center">Controle de Horas</a>
            <ul id="nav-mobile" class="right hide-on-med-and-down">
                <li><a href="#modalUsuario" class="modal-trigger"><i class="material-icons">account_circle</i></a></li>
            </ul>
        </div>
    </nav>
    <div id="modalUsuario" class="modal">
        <div class="modal-content">
            <h4>Informações do Usuário</h4>
            <p>Nome: ${alunoAtual.nome}</p>
            <a href="/" class="waves-effect waves-light btn red">Sair</a>
        </div>
    </div>

    <c:choose>
        <c:when test="${not empty listaItemsAluno}">
            <div class="container" style="align-content: center; width: 70%; padding-top: 50px">
                <div class="row">
                    <table class="highlight">
                        <thead>
                        <tr>
                            <th class="s3">Item</th>
                            <th class="s3">Horas</th>
                            <th class="s3">Página</th>
                            <th class="right"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listaItemsAluno}" var="itemAluno">
                            <tr>
                                <td>${itemAluno.item.descricao}</td>
                                <td>${itemAluno.horas}</td>
                                <td>${itemAluno.pagina}</td>
                                <td class="right">
                                    <a class="waves-effect waves-light btn red" href="/item/excluir?id=${itemAluno.id}"><i class="material-icons center">delete</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="row">
                    <div class="col s12">
                        <a class="waves-effect waves-light btn blue darken-3" href="/item/prepararCadastro">Novo<i class="material-icons right">add</i></a>
                    </div>
                </div>
            </div>
        </c:when>

        <c:when test="${not empty listaItems}">
            <div class="container" style="align-content: center; width: 70%; padding-top: 50px">
                <form action="/item/salvar" method="POST">
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="itemId">
                                <option value="0" selected>Nenhum</option>
                                <c:forEach items="${listaItems}" var="i">
                                    <option value="${i.id}"><c:out value="${i.descricao}"></c:out></option>
                                </c:forEach>
                            </select>
                            <label>Items</label>
                        </div>

                        <input id="alunoId" name="alunoId" hidden="hidden" value="${alunoAtual.id}">

                        <div class="input-field col s12">
                            <input id="horas" name="horas" type="number" class="validate" value="${itemAtual.horas}">
                            <label for="horas">Horas</label>
                        </div>
                        <div class="input-field col s12">
                            <input id="pagina" name="pagina" type="number" class="validate" value="${itemAtual.pagina}">
                            <label for="pagina">Página</label>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <button class="btn waves-effect waves-light blue darken-3" type="submit" name="action">Salvar
                                    <i class="material-icons right">save</i>
                                </button>
                                <a class="waves-effect waves-light btn red" href="/item/listar">Cancelar<i class="material-icons right">cancel</i></a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </c:when>

        <c:when test="${empty alunoAtual}">
            <div class="container" style="align-content: center; width: 70%; padding-top: 50px">
                <form action="/" method="POST">
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="alunoId">
                                <option value="0" selected>Nenhum</option>
                                <c:forEach items="${requestScope.alunos}" var="al">
                                    <option value="${al.id}"> <c:out value="${al.nome}"></c:out></option>
                                </c:forEach>
                            </select>
                            <label>Alunos</label>
                        </div>
                        <p> ${alunoAtual.nome}</p>
                    </div>
                    <div class="row">
                        <div class="col s12">
                            <button class="btn waves-effect waves-light green darken-3" type="submit" name="action">Iniciar
                                <i class="material-icons right">shopping_basket</i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </c:when>
    </c:choose>
</body>
<style>
    #modalUsuario.modal {
        max-width: 25%;
        max-height: 50%;
    }
</style>
</html>
