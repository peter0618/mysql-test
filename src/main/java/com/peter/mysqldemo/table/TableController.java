package com.peter.mysqldemo.table;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {

    final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/selectQuery")
    public String hello(@RequestParam(value="tableSchema", required=false) String tableSchema
                      , @RequestParam(value="tableName", required=false) String tableName){
        System.out.println("tableSchema : " + tableSchema + ", tableName : " + tableName);
        return this.tableService.hello(tableSchema, tableName);
    }
}
