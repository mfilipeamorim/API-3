package api3

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.web.api.ServletAttributes
import org.grails.web.json.JSONObject

import java.time.LocalDate


class FuncionarioService implements ServletAttributes {

    LogService logService
    RestBuilder rest = new RestBuilder()

    Map list() {
        RestResponse resp = rest.get("http://localhost:8080/API2/funcionario/list")
        JSONObject respJson = resp.json as JSONObject
        return respJson
    }

    Map get(Long id) {
        RestResponse resp = rest.get("http://localhost:8080/API2/funcionario/get/${id}")
        JSONObject respJson = resp.json as JSONObject
        return respJson
    }

    Map delete(Long id){
        RestResponse resp = rest.delete("http://localhost:8080/API2/funcionario/delete/${id}")
        JSONObject respJson = resp.json as JSONObject

        def respJsonString = resp.json.toString()
        logService.salvarLog(LocalDate.now(), "DELETE -> " + respJsonString + "FuncionarioID ${id}")
        return respJson
    }

    Map save(){
        JSON jsonBody = request.JSON

        RestResponse resp = rest.post("http://localhost:8080/API2/funcionario/save"){
            body jsonBody
        }
        JSONObject respJson = resp.json as JSONObject

        def respJsonString = resp.json.toString()
        logService.salvarLog(LocalDate.now(), "SAVE -> " + respJsonString + jsonBody)
        return respJson
    }

    Map update(Long id){
        JSON jsonBody = request.JSON

        RestResponse resp = rest.put("http://localhost:8080/API2/funcionario/update/${id}"){
            body jsonBody
        }
        JSONObject respJson = resp.json as JSONObject

        def respJsonString = resp.json.toString()
        logService.salvarLog(LocalDate.now(), "SAVE -> " + respJsonString + jsonBody)
        return respJson
    }
}
