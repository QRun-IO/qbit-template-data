/*******************************************************************************
 ** MetaData producer for the example data sync process.
 *******************************************************************************/
package com.kingsrook.qbits.example.sync;


import java.util.List;
import com.kingsrook.qqq.backend.core.exceptions.QException;
import com.kingsrook.qqq.backend.core.model.metadata.MetaDataProducerInterface;
import com.kingsrook.qqq.backend.core.model.metadata.QInstance;
import com.kingsrook.qqq.backend.core.model.metadata.code.QCodeReference;
import com.kingsrook.qqq.backend.core.model.metadata.fields.QFieldMetaData;
import com.kingsrook.qqq.backend.core.model.metadata.fields.QFieldType;
import com.kingsrook.qqq.backend.core.model.metadata.processes.QBackendStepMetaData;
import com.kingsrook.qqq.backend.core.model.metadata.processes.QFunctionInputMetaData;
import com.kingsrook.qqq.backend.core.model.metadata.processes.QProcessMetaData;


public class ExampleDataSyncProcessMetaDataProducer implements MetaDataProducerInterface<QProcessMetaData>
{
   public static final String NAME = "exampleDataSyncProcess";



   /*******************************************************************************
    ** Produce the process metadata.
    *******************************************************************************/
   @Override
   public QProcessMetaData produce(QInstance qInstance) throws QException
   {
      return new QProcessMetaData()
         .withName(NAME)
         .withLabel("Example Data Sync")
         .withStepList(List.of(
            new QBackendStepMetaData()
               .withName("sync")
               .withCode(new QCodeReference(ExampleDataSyncStep.class))
               .withInputData(new QFunctionInputMetaData()
                  .withField(new QFieldMetaData("tableName", QFieldType.STRING))
                  .withField(new QFieldMetaData("naturalKeyField", QFieldType.STRING))
                  .withField(new QFieldMetaData("dataResourcePath", QFieldType.STRING)))
         ));
   }
}
