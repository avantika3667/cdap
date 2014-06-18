package com.continuuity.data2.dataset2.lib.table.inmemory;

import com.continuuity.api.dataset.DatasetAdmin;
import com.continuuity.data2.dataset2.lib.table.BufferingOrederedTableTest;
import com.continuuity.data2.dataset2.lib.table.ConflictDetection;

/**
 *
 */
public class InMemoryOrderedTableTest extends BufferingOrederedTableTest<InMemoryOrderedTable> {
  @Override
  protected InMemoryOrderedTable getTable(String name, ConflictDetection conflictLevel) throws Exception {
    return new InMemoryOrderedTable(name, conflictLevel);
  }

  @Override
  protected DatasetAdmin getTableAdmin(String name) throws Exception {
    return new InMemoryOrderedTableAdmin(name);
  }
}
