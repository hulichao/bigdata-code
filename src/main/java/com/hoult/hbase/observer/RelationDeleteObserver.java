package com.hoult.hbase.observer;

public class RelationDeleteObserver extends BaseRegionObserver {
    // 重写postDelete
    @Override
    public void postDelete(final ObserverContext<RegionCoprocessorEnvironment> e, final Delete delete, final WALEdit edit, final Durability durability) throws IOException {
        super.postDelete(e, delete, edit, durability);
        //获取表对象
        HTableWrapper roles = (HTableWrapper)e.getEnvironment().getTable(TableName.valueOf("user_relation"));
        //获取执行删除的列族队列集合
        NavigableMap<byte[], List<Cell>> familyCellMap = delete.getFamilyCellMap();
        //通过队列集合
        Set<Map.Entry<byte[], List<Cell>>> entries = familyCellMap.entrySet();
        //循环队列获取cells对象
        for (final Map.Entry<byte[], List<Cell>> entry : entries) {
            System.out.println(Bytes.toString(entry.getKey()));
            List<Cell> cells = entry.getValue();
            //循环cells对象获取每个cell的rowkey以及列族
            for (final Cell cell : cells) {
                byte[] rowkey = CellUtil.cloneRow(cell);
                byte[] qualifier = CellUtil.cloneQualifier(cell);
                //判断是否存在删除的rowkey的cell
                boolean flag = roles.exists(new Get(qualifier).addColumn(Bytes.toBytes("friends"), rowkey));

                if (flag){
                    //生成一个delete对象对需要删除的列族进行删除
                    Delete delQualifier = new Delete(qualifier).addColumn(Bytes.toBytes("friends"), rowkey);
                    roles.delete(delQualifier);
                }

            }
        }
    }
}
