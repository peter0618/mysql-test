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

    /**
     * 기본적인 조회 쿼리를 만들어줍니다.
     * @param tableSchema
     * @param tableName
     * @return
     */
    @GetMapping("/selectQuery")
    public String selectQuery(@RequestParam(value="tableSchema", required=false) String tableSchema
                      , @RequestParam(value="tableName", required=false) String tableName){
        System.out.println("tableSchema : " + tableSchema + ", tableName : " + tableName);
        return this.tableService.selectQuery(tableSchema, tableName);
    }

    /**
     * 기본적인 INSERT 쿼리를 만들어줍니다. (PreparedStatement 형식)
     * @param tableSchema
     * @param tableName
     * @return
     */
    @GetMapping("/insertQuery")
    public String insertQuery(@RequestParam(value="tableSchema", required=false) String tableSchema
            , @RequestParam(value="tableName", required=false) String tableName){
        System.out.println("tableSchema : " + tableSchema + ", tableName : " + tableName);
        return this.tableService.insertQuery(tableSchema, tableName);
    }

    /**
     * 기본적인 UPDATE 쿼리를 만들어줍니다.
     * @param tableSchema
     * @param tableName
     * @return
     */
    @GetMapping("updateQuery")
    public String updateQuery(@RequestParam(value="tableSchema", required=false) String tableSchema
            , @RequestParam(value="tableName", required=false) String tableName){
        System.out.println("tableSchema : " + tableSchema + ", tableName : " + tableName);
        return this.tableService.updateQuery(tableSchema, tableName);
    }
}
