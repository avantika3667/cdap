package com.continuuity.test.app;

import com.continuuity.data2.dataset2.lib.AbstractDataset;
import com.continuuity.data2.dataset2.lib.CompositeDatasetDefinition;
import com.continuuity.data2.dataset2.lib.table.TableDefinition;
import com.continuuity.internal.data.dataset.DatasetDefinition;
import com.continuuity.internal.data.dataset.DatasetSpecification;
import com.continuuity.internal.data.dataset.lib.table.Get;
import com.continuuity.internal.data.dataset.lib.table.Increment;
import com.continuuity.internal.data.dataset.lib.table.Table;
import com.continuuity.internal.data.dataset.module.DatasetDefinitionRegistry;
import com.continuuity.internal.data.dataset.module.DatasetModule;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;

public class UniqueCountTableDefinition
  extends CompositeDatasetDefinition<UniqueCountTableDefinition.UniqueCountTable> {

  public UniqueCountTableDefinition(String name, DatasetDefinition<? extends Table, ?> tableDef) {
    super(name, ImmutableMap.of("entryCountTable", tableDef,
                                "uniqueCountTable", tableDef));
  }

  @Override
  public UniqueCountTable getDataset(DatasetSpecification spec) throws IOException {
    return new UniqueCountTable(spec.getName(),
                                getDataset("entryCountTable", Table.class, spec),
                                getDataset("uniqueCountTable", Table.class, spec));
  }

  public static class UniqueCountTable extends AbstractDataset {

    private final Table entryCountTable;
    private final Table uniqueCountTable;

    public UniqueCountTable(String instanceName,
                            Table entryCountTable,
                            Table uniqueCountTable) {
      super(instanceName, entryCountTable, uniqueCountTable);
      this.entryCountTable = entryCountTable;
      this.uniqueCountTable = uniqueCountTable;
    }

    public void updateUniqueCount(String entry) {
      long newCount = entryCountTable.increment(new Increment(entry, "count", 1L)).getInt("count");
      if (newCount == 1L) {
        uniqueCountTable.increment(new Increment("unique_count", "count", 1L));
      }
    }

    public Long readUniqueCount() {
      return uniqueCountTable.get(new Get("unique_count", "count"))
        .getLong("count", 0);
    }

  }

  /**
   * Dataset module
   */
  public static class Module implements DatasetModule {
    @Override
    public void register(DatasetDefinitionRegistry registry) {
      TableDefinition tableDefinition = registry.get("table");
      UniqueCountTableDefinition keyValueTable = new UniqueCountTableDefinition("uniqueCountTable", tableDefinition);
      registry.add(keyValueTable);
    }
  }
}

