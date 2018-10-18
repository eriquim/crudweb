
var host = "http://localhost:8080/CrudWeb";
 
function inserir() {
	console.log("entrando inserir...");
	var novaTarefa = document.getElementById("nome").value; 
	var novoResponsavel = document.getElementById("responsavel").value;
	var task = {
		"id" : undefined,
		"nome" : novaTarefa,
		"responsavel" : novoResponsavel,
		"statusTarefa": "TO_DO"
	};
	var callback = undefined;
	enviarRequisicaoAjax("/newTarefa",JSON.stringify(task),reRenderAll);
	alert('Tarefa inserida!');
}


function alterar(id,status) {
	console.log("entrando alterar...");
	var novaTarefa = document.getElementById("nome"+id).value; 
	var novoResponsavel = document.getElementById("responsavel"+id).value;
	var task = {
		"id" : id,
		"nome" : novaTarefa,
		"responsavel" : novoResponsavel,
		"statusTarefa": status.toUpperCase()
	};
	var callback = undefined;
	enviarRequisicaoAjax("/alterarTarefa",JSON.stringify(task),reRenderAll);
	alert('Tarefa alterada!');

}

function getAllRequisicoes(){
	enviarRequisicaoAjax("/getTarefaAllServlet","", reRenderAll);
}

function alterarStatusTarefaRequisicaoAjax(id,novoStatus,callback){
	var xhttp = new XMLHttpRequest();
	xhttp.open('POST', host+"/alterarStatus?id="+id+"&novoStatus="+novoStatus, true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onreadystatechange = function() {
		 if (this.readyState === 4 && this.status === 200) {
			  if(callback){
				  callback(this.responseText);
			  }
			  console.log("callback: "+this.responseText);
		  }
	  }
	xhttp.send();
}
function removerTarefaRequisicaoAjax(id,callback){
	var xhttp = new XMLHttpRequest();
	xhttp.open('POST', host+"/removerTarefa?id="+id, true);
	xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhttp.onreadystatechange = function() {
		 if (this.readyState === 4 && this.status === 200) {
			  if(callback){
				  callback(this.responseText);
			  }
			  console.log("callback: "+this.responseText);
		  }
	  }
	xhttp.send();
}


function enviarRequisicaoAjax(url, data, callback) {
	  var xmlDoc = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	  var json_upload = "json=" + data;
	  var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
	  xmlDoc.open('POST', host+url);
	  xmlDoc.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xmlDoc.onreadystatechange = function() {
		  if (this.readyState === 4 && this.status === 200) {
			  if(callback){
				  callback(this.responseText);
			  }
			  console.log("callback: "+this.responseText);
		  }
	  }
	  if(data){
		  xmlDoc.send(json_upload);
	  } else{
		  xmlDoc.send();
	  }
}

function subir(id,statusTarefa){
	
	if(statusTarefa == "to_do"){
		console.log("ja ta no topo - não faz nada");
	} else if(statusTarefa == "doing"){
		console.log("subindo pra to_do");
		alterarStatusTarefaRequisicaoAjax(id, "to_do", reRenderAll);
	} else if(statusTarefa == "done"){
		console.log("subindo para doing");
		alterarStatusTarefaRequisicaoAjax(id, "doing", reRenderAll);
	}
}

function descer(id,statusTarefa){
	if(statusTarefa == "to_do"){
		console.log("descendo para doing");
		alterarStatusTarefaRequisicaoAjax(id, "doing", reRenderAll);
	} else if(statusTarefa == "doing"){
		console.log("descendo para done");
		alterarStatusTarefaRequisicaoAjax(id, "done", reRenderAll);
	} else if(statusTarefa == "done"){
		console.log("ja ta no pé- não faz nada");
	}
}

function remover(id){
	console.log("removendo.."+id);
	removerTarefaRequisicaoAjax(id, reRenderAll);
	alert('Tarefa excluída');
}


function reRenderAll(texto){
	var jsonTodo = [];
	var jsonDoing = [];
	var jsonDone = [];
	
	//console.log("Objeto callback retornado:"+texto);
	var jsonListObj = JSON.parse(texto);
	
	if(jsonListObj){
		for (i in jsonListObj){
			var jsonObject = jsonListObj[i];
			if(jsonObject.statusTarefa == "TO_DO"){
				jsonTodo.push(jsonObject);
			} else if(jsonObject.statusTarefa == "DOING"){
				jsonDoing.push(jsonObject);
			} else if(jsonObject.statusTarefa == "DONE"){
				jsonDone.push(jsonObject);
			}
		}
		renderHTML(jsonTodo, "to_do");
		renderHTML(jsonDoing, "doing");
		renderHTML(jsonDone, "done");
	}
}


function renderHTML(data, component) {
	var taskContainer = document.getElementById(component),
	htmlString = "<ul>";
	for (i in data) {
		htmlString += "<li id=\""+data[i].id+"\"style=\"white-space:nowrap\">" +
			"<input id='nome"+data[i].id+"' type='text' value='"+ data[i].nome + "' size='10'>"+
			"<input id='responsavel"+data[i].id+"' type='text' value='" + data[i].responsavel +"' size='12'><br/>"+
			"<button onclick=\"subir("+data[i].id+",'"+component.trim()+"');\">&larr;</button>"+
			"<button onclick=\"descer("+data[i].id+",'"+component.trim()+"');\">&rarr;</button> "+
			"<button onclick=\"alterar("+data[i].id+",'"+component.trim()+"');\">Alterar</button>"+
			"<button onclick=\"remover("+data[i].id+");\">Remover</button><br/><br/>"+
		"</li>"; 
	}
	htmlString += "</ul>";
	taskContainer.innerHTML=htmlString; 
}

getAllRequisicoes();
