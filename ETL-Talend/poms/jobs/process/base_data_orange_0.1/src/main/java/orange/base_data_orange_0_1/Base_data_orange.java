// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package orange.base_data_orange_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Base_data_orange Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Base_data_orange implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (test_RowSeparator != null) {

				this.setProperty("test_RowSeparator", test_RowSeparator.toString());

			}

			if (test_File != null) {

				this.setProperty("test_File", test_File.toString());

			}

			if (test_Encoding != null) {

				this.setProperty("test_Encoding", test_Encoding.toString());

			}

			if (test_FieldSeparator != null) {

				this.setProperty("test_FieldSeparator", test_FieldSeparator.toString());

			}

			if (test_Header != null) {

				this.setProperty("test_Header", test_Header.toString());

			}

			if (Extract4_File != null) {

				this.setProperty("Extract4_File", Extract4_File.toString());

			}

			if (Extract4_Encoding != null) {

				this.setProperty("Extract4_Encoding", Extract4_Encoding.toString());

			}

			if (Extract4_Header != null) {

				this.setProperty("Extract4_Header", Extract4_Header.toString());

			}

			if (Extract4_DecimalSeparator != null) {

				this.setProperty("Extract4_DecimalSeparator", Extract4_DecimalSeparator.toString());

			}

			if (Extract4_LastColumn != null) {

				this.setProperty("Extract4_LastColumn", Extract4_LastColumn.toString());

			}

			if (Extract4_ThousandSeparator != null) {

				this.setProperty("Extract4_ThousandSeparator", Extract4_ThousandSeparator.toString());

			}

			if (Extract4_FirstColumn != null) {

				this.setProperty("Extract4_FirstColumn", Extract4_FirstColumn.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String test_RowSeparator;

		public String getTest_RowSeparator() {
			return this.test_RowSeparator;
		}

		public String test_File;

		public String getTest_File() {
			return this.test_File;
		}

		public String test_Encoding;

		public String getTest_Encoding() {
			return this.test_Encoding;
		}

		public String test_FieldSeparator;

		public String getTest_FieldSeparator() {
			return this.test_FieldSeparator;
		}

		public Integer test_Header;

		public Integer getTest_Header() {
			return this.test_Header;
		}

		public String Extract4_File;

		public String getExtract4_File() {
			return this.Extract4_File;
		}

		public String Extract4_Encoding;

		public String getExtract4_Encoding() {
			return this.Extract4_Encoding;
		}

		public Integer Extract4_Header;

		public Integer getExtract4_Header() {
			return this.Extract4_Header;
		}

		public String Extract4_DecimalSeparator;

		public String getExtract4_DecimalSeparator() {
			return this.Extract4_DecimalSeparator;
		}

		public Integer Extract4_LastColumn;

		public Integer getExtract4_LastColumn() {
			return this.Extract4_LastColumn;
		}

		public String Extract4_ThousandSeparator;

		public String getExtract4_ThousandSeparator() {
			return this.Extract4_ThousandSeparator;
		}

		public Integer Extract4_FirstColumn;

		public Integer getExtract4_FirstColumn() {
			return this.Extract4_FirstColumn;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Base_data_orange";
	private final String projectName = "ORANGE";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Base_data_orange.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Base_data_orange.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputExcel_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_12_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_13_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_14_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_15_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_17_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_18_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_19_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row11_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class orangeStruct implements routines.system.IPersistableRow<orangeStruct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer Client_Prospect_id;

		public Integer getClient_Prospect_id() {
			return this.Client_Prospect_id;
		}

		public Integer type_acces_id;

		public Integer getType_acces_id() {
			return this.type_acces_id;
		}

		public Integer type_operation_id;

		public Integer getType_operation_id() {
			return this.type_operation_id;
		}

		public java.util.Date DIM_Date_id;

		public java.util.Date getDIM_Date_id() {
			return this.DIM_Date_id;
		}

		public Integer equipe_id;

		public Integer getEquipe_id() {
			return this.equipe_id;
		}

		public Integer sujet_id;

		public Integer getSujet_id() {
			return this.sujet_id;
		}

		public Integer statu_final_id;

		public Integer getStatu_final_id() {
			return this.statu_final_id;
		}

		public Integer phase_de_l_opportunite_id;

		public Integer getPhase_de_l_opportunite_id() {
			return this.phase_de_l_opportunite_id;
		}

		public Integer membre_equipe_id;

		public Integer getMembre_equipe_id() {
			return this.membre_equipe_id;
		}

		public Float Total_final;

		public Float getTotal_final() {
			return this.Total_final;
		}

		public Float Montant_total;

		public Float getMontant_total() {
			return this.Montant_total;
		}

		public Float Quantite;

		public Float getQuantite() {
			return this.Quantite;
		}

		public Float Prix_unitaire;

		public Float getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public Float Remise;

		public Float getRemise() {
			return this.Remise;
		}

		public Float Montant_de_la_remise_manuelle;

		public Float getMontant_de_la_remise_manuelle() {
			return this.Montant_de_la_remise_manuelle;
		}

		public Float Valeur_ordering;

		public Float getValeur_ordering() {
			return this.Valeur_ordering;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Client_Prospect_id == null) ? 0 : this.Client_Prospect_id.hashCode());

				result = prime * result + ((this.type_acces_id == null) ? 0 : this.type_acces_id.hashCode());

				result = prime * result + ((this.type_operation_id == null) ? 0 : this.type_operation_id.hashCode());

				result = prime * result + ((this.DIM_Date_id == null) ? 0 : this.DIM_Date_id.hashCode());

				result = prime * result + ((this.equipe_id == null) ? 0 : this.equipe_id.hashCode());

				result = prime * result + ((this.sujet_id == null) ? 0 : this.sujet_id.hashCode());

				result = prime * result + ((this.statu_final_id == null) ? 0 : this.statu_final_id.hashCode());

				result = prime * result
						+ ((this.phase_de_l_opportunite_id == null) ? 0 : this.phase_de_l_opportunite_id.hashCode());

				result = prime * result + ((this.membre_equipe_id == null) ? 0 : this.membre_equipe_id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final orangeStruct other = (orangeStruct) obj;

			if (this.Client_Prospect_id == null) {
				if (other.Client_Prospect_id != null)
					return false;

			} else if (!this.Client_Prospect_id.equals(other.Client_Prospect_id))

				return false;

			if (this.type_acces_id == null) {
				if (other.type_acces_id != null)
					return false;

			} else if (!this.type_acces_id.equals(other.type_acces_id))

				return false;

			if (this.type_operation_id == null) {
				if (other.type_operation_id != null)
					return false;

			} else if (!this.type_operation_id.equals(other.type_operation_id))

				return false;

			if (this.DIM_Date_id == null) {
				if (other.DIM_Date_id != null)
					return false;

			} else if (!this.DIM_Date_id.equals(other.DIM_Date_id))

				return false;

			if (this.equipe_id == null) {
				if (other.equipe_id != null)
					return false;

			} else if (!this.equipe_id.equals(other.equipe_id))

				return false;

			if (this.sujet_id == null) {
				if (other.sujet_id != null)
					return false;

			} else if (!this.sujet_id.equals(other.sujet_id))

				return false;

			if (this.statu_final_id == null) {
				if (other.statu_final_id != null)
					return false;

			} else if (!this.statu_final_id.equals(other.statu_final_id))

				return false;

			if (this.phase_de_l_opportunite_id == null) {
				if (other.phase_de_l_opportunite_id != null)
					return false;

			} else if (!this.phase_de_l_opportunite_id.equals(other.phase_de_l_opportunite_id))

				return false;

			if (this.membre_equipe_id == null) {
				if (other.membre_equipe_id != null)
					return false;

			} else if (!this.membre_equipe_id.equals(other.membre_equipe_id))

				return false;

			return true;
		}

		public void copyDataTo(orangeStruct other) {

			other.Client_Prospect_id = this.Client_Prospect_id;
			other.type_acces_id = this.type_acces_id;
			other.type_operation_id = this.type_operation_id;
			other.DIM_Date_id = this.DIM_Date_id;
			other.equipe_id = this.equipe_id;
			other.sujet_id = this.sujet_id;
			other.statu_final_id = this.statu_final_id;
			other.phase_de_l_opportunite_id = this.phase_de_l_opportunite_id;
			other.membre_equipe_id = this.membre_equipe_id;
			other.Total_final = this.Total_final;
			other.Montant_total = this.Montant_total;
			other.Quantite = this.Quantite;
			other.Prix_unitaire = this.Prix_unitaire;
			other.Remise = this.Remise;
			other.Montant_de_la_remise_manuelle = this.Montant_de_la_remise_manuelle;
			other.Valeur_ordering = this.Valeur_ordering;

		}

		public void copyKeysDataTo(orangeStruct other) {

			other.Client_Prospect_id = this.Client_Prospect_id;
			other.type_acces_id = this.type_acces_id;
			other.type_operation_id = this.type_operation_id;
			other.DIM_Date_id = this.DIM_Date_id;
			other.equipe_id = this.equipe_id;
			other.sujet_id = this.sujet_id;
			other.statu_final_id = this.statu_final_id;
			other.phase_de_l_opportunite_id = this.phase_de_l_opportunite_id;
			other.membre_equipe_id = this.membre_equipe_id;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect_id = readInteger(dis);

					this.type_acces_id = readInteger(dis);

					this.type_operation_id = readInteger(dis);

					this.DIM_Date_id = readDate(dis);

					this.equipe_id = readInteger(dis);

					this.sujet_id = readInteger(dis);

					this.statu_final_id = readInteger(dis);

					this.phase_de_l_opportunite_id = readInteger(dis);

					this.membre_equipe_id = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_total = null;
					} else {
						this.Montant_total = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise = null;
					} else {
						this.Remise = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_de_la_remise_manuelle = null;
					} else {
						this.Montant_de_la_remise_manuelle = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Valeur_ordering = null;
					} else {
						this.Valeur_ordering = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect_id = readInteger(dis);

					this.type_acces_id = readInteger(dis);

					this.type_operation_id = readInteger(dis);

					this.DIM_Date_id = readDate(dis);

					this.equipe_id = readInteger(dis);

					this.sujet_id = readInteger(dis);

					this.statu_final_id = readInteger(dis);

					this.phase_de_l_opportunite_id = readInteger(dis);

					this.membre_equipe_id = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_total = null;
					} else {
						this.Montant_total = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise = null;
					} else {
						this.Remise = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_de_la_remise_manuelle = null;
					} else {
						this.Montant_de_la_remise_manuelle = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Valeur_ordering = null;
					} else {
						this.Valeur_ordering = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Client_Prospect_id, dos);

				// Integer

				writeInteger(this.type_acces_id, dos);

				// Integer

				writeInteger(this.type_operation_id, dos);

				// java.util.Date

				writeDate(this.DIM_Date_id, dos);

				// Integer

				writeInteger(this.equipe_id, dos);

				// Integer

				writeInteger(this.sujet_id, dos);

				// Integer

				writeInteger(this.statu_final_id, dos);

				// Integer

				writeInteger(this.phase_de_l_opportunite_id, dos);

				// Integer

				writeInteger(this.membre_equipe_id, dos);

				// Float

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// Float

				if (this.Montant_total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_total);
				}

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Remise == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise);
				}

				// Float

				if (this.Montant_de_la_remise_manuelle == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_de_la_remise_manuelle);
				}

				// Float

				if (this.Valeur_ordering == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Valeur_ordering);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.Client_Prospect_id, dos);

				// Integer

				writeInteger(this.type_acces_id, dos);

				// Integer

				writeInteger(this.type_operation_id, dos);

				// java.util.Date

				writeDate(this.DIM_Date_id, dos);

				// Integer

				writeInteger(this.equipe_id, dos);

				// Integer

				writeInteger(this.sujet_id, dos);

				// Integer

				writeInteger(this.statu_final_id, dos);

				// Integer

				writeInteger(this.phase_de_l_opportunite_id, dos);

				// Integer

				writeInteger(this.membre_equipe_id, dos);

				// Float

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// Float

				if (this.Montant_total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_total);
				}

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Remise == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise);
				}

				// Float

				if (this.Montant_de_la_remise_manuelle == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_de_la_remise_manuelle);
				}

				// Float

				if (this.Valeur_ordering == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Valeur_ordering);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Client_Prospect_id=" + String.valueOf(Client_Prospect_id));
			sb.append(",type_acces_id=" + String.valueOf(type_acces_id));
			sb.append(",type_operation_id=" + String.valueOf(type_operation_id));
			sb.append(",DIM_Date_id=" + String.valueOf(DIM_Date_id));
			sb.append(",equipe_id=" + String.valueOf(equipe_id));
			sb.append(",sujet_id=" + String.valueOf(sujet_id));
			sb.append(",statu_final_id=" + String.valueOf(statu_final_id));
			sb.append(",phase_de_l_opportunite_id=" + String.valueOf(phase_de_l_opportunite_id));
			sb.append(",membre_equipe_id=" + String.valueOf(membre_equipe_id));
			sb.append(",Total_final=" + String.valueOf(Total_final));
			sb.append(",Montant_total=" + String.valueOf(Montant_total));
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Prix_unitaire=" + String.valueOf(Prix_unitaire));
			sb.append(",Remise=" + String.valueOf(Remise));
			sb.append(",Montant_de_la_remise_manuelle=" + String.valueOf(Montant_de_la_remise_manuelle));
			sb.append(",Valeur_ordering=" + String.valueOf(Valeur_ordering));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(orangeStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Client_Prospect_id, other.Client_Prospect_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.type_acces_id, other.type_acces_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.type_operation_id, other.type_operation_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.DIM_Date_id, other.DIM_Date_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.equipe_id, other.equipe_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.sujet_id, other.sujet_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.statu_final_id, other.statu_final_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.phase_de_l_opportunite_id, other.phase_de_l_opportunite_id);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.membre_equipe_id, other.membre_equipe_id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class CorrectionStruct implements routines.system.IPersistableRow<CorrectionStruct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];

		public String Client_Prospect;

		public String getClient_Prospect() {
			return this.Client_Prospect;
		}

		public String Sujet;

		public String getSujet() {
			return this.Sujet;
		}

		public String Acces;

		public String getAcces() {
			return this.Acces;
		}

		public String Type_d_acces;

		public String getType_d_acces() {
			return this.Type_d_acces;
		}

		public String Account_Manager__Client_Prospect;

		public String getAccount_Manager__Client_Prospect() {
			return this.Account_Manager__Client_Prospect;
		}

		public java.util.Date Cree_le;

		public java.util.Date getCree_le() {
			return this.Cree_le;
		}

		public String Mois_et_annee_de_creation;

		public String getMois_et_annee_de_creation() {
			return this.Mois_et_annee_de_creation;
		}

		public String Cree_par;

		public String getCree_par() {
			return this.Cree_par;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public String Proprietaire;

		public String getProprietaire() {
			return this.Proprietaire;
		}

		public String Phase_de_l_opportunite;

		public String getPhase_de_l_opportunite() {
			return this.Phase_de_l_opportunite;
		}

		public Float Valeur_ordering;

		public Float getValeur_ordering() {
			return this.Valeur_ordering;
		}

		public String Statut;

		public String getStatut() {
			return this.Statut;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		public String Tarifs;

		public String getTarifs() {
			return this.Tarifs;
		}

		public Float Montant_total;

		public Float getMontant_total() {
			return this.Montant_total;
		}

		public String Parent__Produit_existant;

		public String getParent__Produit_existant() {
			return this.Parent__Produit_existant;
		}

		public String Produit_existant;

		public String getProduit_existant() {
			return this.Produit_existant;
		}

		public String Operation;

		public String getOperation() {
			return this.Operation;
		}

		public String Type_d_operation;

		public String getType_d_operation() {
			return this.Type_d_operation;
		}

		public Float Prix_unitaire;

		public Float getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public Float Quantite;

		public Float getQuantite() {
			return this.Quantite;
		}

		public Float Remise;

		public Float getRemise() {
			return this.Remise;
		}

		public Float Montant_de_la_remise_manuelle;

		public Float getMontant_de_la_remise_manuelle() {
			return this.Montant_de_la_remise_manuelle;
		}

		public Float Total_final;

		public Float getTotal_final() {
			return this.Total_final;
		}

		public java.util.Date Modifie_le;

		public java.util.Date getModifie_le() {
			return this.Modifie_le;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

					this.Sujet = readString(dis);

					this.Acces = readString(dis);

					this.Type_d_acces = readString(dis);

					this.Account_Manager__Client_Prospect = readString(dis);

					this.Cree_le = readDate(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Proprietaire = readString(dis);

					this.Phase_de_l_opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Valeur_ordering = null;
					} else {
						this.Valeur_ordering = dis.readFloat();
					}

					this.Statut = readString(dis);

					this.Staut_Final = readString(dis);

					this.Tarifs = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Montant_total = null;
					} else {
						this.Montant_total = dis.readFloat();
					}

					this.Parent__Produit_existant = readString(dis);

					this.Produit_existant = readString(dis);

					this.Operation = readString(dis);

					this.Type_d_operation = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise = null;
					} else {
						this.Remise = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_de_la_remise_manuelle = null;
					} else {
						this.Montant_de_la_remise_manuelle = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					this.Modifie_le = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

					this.Sujet = readString(dis);

					this.Acces = readString(dis);

					this.Type_d_acces = readString(dis);

					this.Account_Manager__Client_Prospect = readString(dis);

					this.Cree_le = readDate(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Proprietaire = readString(dis);

					this.Phase_de_l_opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Valeur_ordering = null;
					} else {
						this.Valeur_ordering = dis.readFloat();
					}

					this.Statut = readString(dis);

					this.Staut_Final = readString(dis);

					this.Tarifs = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Montant_total = null;
					} else {
						this.Montant_total = dis.readFloat();
					}

					this.Parent__Produit_existant = readString(dis);

					this.Produit_existant = readString(dis);

					this.Operation = readString(dis);

					this.Type_d_operation = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise = null;
					} else {
						this.Remise = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_de_la_remise_manuelle = null;
					} else {
						this.Montant_de_la_remise_manuelle = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					this.Modifie_le = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Client_Prospect, dos);

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Acces, dos);

				// String

				writeString(this.Type_d_acces, dos);

				// String

				writeString(this.Account_Manager__Client_Prospect, dos);

				// java.util.Date

				writeDate(this.Cree_le, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Proprietaire, dos);

				// String

				writeString(this.Phase_de_l_opportunite, dos);

				// Float

				if (this.Valeur_ordering == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Valeur_ordering);
				}

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Tarifs, dos);

				// Float

				if (this.Montant_total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_total);
				}

				// String

				writeString(this.Parent__Produit_existant, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Operation, dos);

				// String

				writeString(this.Type_d_operation, dos);

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Remise == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise);
				}

				// Float

				if (this.Montant_de_la_remise_manuelle == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_de_la_remise_manuelle);
				}

				// Float

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// java.util.Date

				writeDate(this.Modifie_le, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Client_Prospect, dos);

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Acces, dos);

				// String

				writeString(this.Type_d_acces, dos);

				// String

				writeString(this.Account_Manager__Client_Prospect, dos);

				// java.util.Date

				writeDate(this.Cree_le, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Proprietaire, dos);

				// String

				writeString(this.Phase_de_l_opportunite, dos);

				// Float

				if (this.Valeur_ordering == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Valeur_ordering);
				}

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Tarifs, dos);

				// Float

				if (this.Montant_total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_total);
				}

				// String

				writeString(this.Parent__Produit_existant, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Operation, dos);

				// String

				writeString(this.Type_d_operation, dos);

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Remise == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise);
				}

				// Float

				if (this.Montant_de_la_remise_manuelle == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_de_la_remise_manuelle);
				}

				// Float

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// java.util.Date

				writeDate(this.Modifie_le, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Client_Prospect=" + Client_Prospect);
			sb.append(",Sujet=" + Sujet);
			sb.append(",Acces=" + Acces);
			sb.append(",Type_d_acces=" + Type_d_acces);
			sb.append(",Account_Manager__Client_Prospect=" + Account_Manager__Client_Prospect);
			sb.append(",Cree_le=" + String.valueOf(Cree_le));
			sb.append(",Mois_et_annee_de_creation=" + Mois_et_annee_de_creation);
			sb.append(",Cree_par=" + Cree_par);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Proprietaire=" + Proprietaire);
			sb.append(",Phase_de_l_opportunite=" + Phase_de_l_opportunite);
			sb.append(",Valeur_ordering=" + String.valueOf(Valeur_ordering));
			sb.append(",Statut=" + Statut);
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append(",Tarifs=" + Tarifs);
			sb.append(",Montant_total=" + String.valueOf(Montant_total));
			sb.append(",Parent__Produit_existant=" + Parent__Produit_existant);
			sb.append(",Produit_existant=" + Produit_existant);
			sb.append(",Operation=" + Operation);
			sb.append(",Type_d_operation=" + Type_d_operation);
			sb.append(",Prix_unitaire=" + String.valueOf(Prix_unitaire));
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Remise=" + String.valueOf(Remise));
			sb.append(",Montant_de_la_remise_manuelle=" + String.valueOf(Montant_de_la_remise_manuelle));
			sb.append(",Total_final=" + String.valueOf(Total_final));
			sb.append(",Modifie_le=" + String.valueOf(Modifie_le));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(CorrectionStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];

		public String Client_Prospect;

		public String getClient_Prospect() {
			return this.Client_Prospect;
		}

		public String Sujet;

		public String getSujet() {
			return this.Sujet;
		}

		public String Acces;

		public String getAcces() {
			return this.Acces;
		}

		public String Type_d_acces;

		public String getType_d_acces() {
			return this.Type_d_acces;
		}

		public String Account_Manager__Client_Prospect;

		public String getAccount_Manager__Client_Prospect() {
			return this.Account_Manager__Client_Prospect;
		}

		public java.util.Date Cree_le;

		public java.util.Date getCree_le() {
			return this.Cree_le;
		}

		public String Mois_et_annee_de_creation;

		public String getMois_et_annee_de_creation() {
			return this.Mois_et_annee_de_creation;
		}

		public String Cree_par;

		public String getCree_par() {
			return this.Cree_par;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public String Proprietaire;

		public String getProprietaire() {
			return this.Proprietaire;
		}

		public String Phase_de_l_opportunite;

		public String getPhase_de_l_opportunite() {
			return this.Phase_de_l_opportunite;
		}

		public Float Valeur_ordering;

		public Float getValeur_ordering() {
			return this.Valeur_ordering;
		}

		public String Statut;

		public String getStatut() {
			return this.Statut;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		public String Tarifs;

		public String getTarifs() {
			return this.Tarifs;
		}

		public Float Montant_total;

		public Float getMontant_total() {
			return this.Montant_total;
		}

		public String Parent__Produit_existant;

		public String getParent__Produit_existant() {
			return this.Parent__Produit_existant;
		}

		public String Produit_existant;

		public String getProduit_existant() {
			return this.Produit_existant;
		}

		public String Operation;

		public String getOperation() {
			return this.Operation;
		}

		public String Type_d_operation;

		public String getType_d_operation() {
			return this.Type_d_operation;
		}

		public Float Prix_unitaire;

		public Float getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public Float Quantite;

		public Float getQuantite() {
			return this.Quantite;
		}

		public Float Remise;

		public Float getRemise() {
			return this.Remise;
		}

		public Float Montant_de_la_remise_manuelle;

		public Float getMontant_de_la_remise_manuelle() {
			return this.Montant_de_la_remise_manuelle;
		}

		public Float Total_final;

		public Float getTotal_final() {
			return this.Total_final;
		}

		public String Mois_et_annee_de_modification;

		public String getMois_et_annee_de_modification() {
			return this.Mois_et_annee_de_modification;
		}

		public java.util.Date Modifie_le;

		public java.util.Date getModifie_le() {
			return this.Modifie_le;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

					this.Sujet = readString(dis);

					this.Acces = readString(dis);

					this.Type_d_acces = readString(dis);

					this.Account_Manager__Client_Prospect = readString(dis);

					this.Cree_le = readDate(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Proprietaire = readString(dis);

					this.Phase_de_l_opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Valeur_ordering = null;
					} else {
						this.Valeur_ordering = dis.readFloat();
					}

					this.Statut = readString(dis);

					this.Staut_Final = readString(dis);

					this.Tarifs = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Montant_total = null;
					} else {
						this.Montant_total = dis.readFloat();
					}

					this.Parent__Produit_existant = readString(dis);

					this.Produit_existant = readString(dis);

					this.Operation = readString(dis);

					this.Type_d_operation = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise = null;
					} else {
						this.Remise = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_de_la_remise_manuelle = null;
					} else {
						this.Montant_de_la_remise_manuelle = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					this.Mois_et_annee_de_modification = readString(dis);

					this.Modifie_le = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

					this.Sujet = readString(dis);

					this.Acces = readString(dis);

					this.Type_d_acces = readString(dis);

					this.Account_Manager__Client_Prospect = readString(dis);

					this.Cree_le = readDate(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Proprietaire = readString(dis);

					this.Phase_de_l_opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Valeur_ordering = null;
					} else {
						this.Valeur_ordering = dis.readFloat();
					}

					this.Statut = readString(dis);

					this.Staut_Final = readString(dis);

					this.Tarifs = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Montant_total = null;
					} else {
						this.Montant_total = dis.readFloat();
					}

					this.Parent__Produit_existant = readString(dis);

					this.Produit_existant = readString(dis);

					this.Operation = readString(dis);

					this.Type_d_operation = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise = null;
					} else {
						this.Remise = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_de_la_remise_manuelle = null;
					} else {
						this.Montant_de_la_remise_manuelle = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					this.Mois_et_annee_de_modification = readString(dis);

					this.Modifie_le = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Client_Prospect, dos);

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Acces, dos);

				// String

				writeString(this.Type_d_acces, dos);

				// String

				writeString(this.Account_Manager__Client_Prospect, dos);

				// java.util.Date

				writeDate(this.Cree_le, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Proprietaire, dos);

				// String

				writeString(this.Phase_de_l_opportunite, dos);

				// Float

				if (this.Valeur_ordering == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Valeur_ordering);
				}

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Tarifs, dos);

				// Float

				if (this.Montant_total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_total);
				}

				// String

				writeString(this.Parent__Produit_existant, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Operation, dos);

				// String

				writeString(this.Type_d_operation, dos);

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Remise == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise);
				}

				// Float

				if (this.Montant_de_la_remise_manuelle == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_de_la_remise_manuelle);
				}

				// Float

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// String

				writeString(this.Mois_et_annee_de_modification, dos);

				// java.util.Date

				writeDate(this.Modifie_le, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Client_Prospect, dos);

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Acces, dos);

				// String

				writeString(this.Type_d_acces, dos);

				// String

				writeString(this.Account_Manager__Client_Prospect, dos);

				// java.util.Date

				writeDate(this.Cree_le, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Proprietaire, dos);

				// String

				writeString(this.Phase_de_l_opportunite, dos);

				// Float

				if (this.Valeur_ordering == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Valeur_ordering);
				}

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Tarifs, dos);

				// Float

				if (this.Montant_total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_total);
				}

				// String

				writeString(this.Parent__Produit_existant, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Operation, dos);

				// String

				writeString(this.Type_d_operation, dos);

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Remise == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise);
				}

				// Float

				if (this.Montant_de_la_remise_manuelle == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_de_la_remise_manuelle);
				}

				// Float

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// String

				writeString(this.Mois_et_annee_de_modification, dos);

				// java.util.Date

				writeDate(this.Modifie_le, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Client_Prospect=" + Client_Prospect);
			sb.append(",Sujet=" + Sujet);
			sb.append(",Acces=" + Acces);
			sb.append(",Type_d_acces=" + Type_d_acces);
			sb.append(",Account_Manager__Client_Prospect=" + Account_Manager__Client_Prospect);
			sb.append(",Cree_le=" + String.valueOf(Cree_le));
			sb.append(",Mois_et_annee_de_creation=" + Mois_et_annee_de_creation);
			sb.append(",Cree_par=" + Cree_par);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Proprietaire=" + Proprietaire);
			sb.append(",Phase_de_l_opportunite=" + Phase_de_l_opportunite);
			sb.append(",Valeur_ordering=" + String.valueOf(Valeur_ordering));
			sb.append(",Statut=" + Statut);
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append(",Tarifs=" + Tarifs);
			sb.append(",Montant_total=" + String.valueOf(Montant_total));
			sb.append(",Parent__Produit_existant=" + Parent__Produit_existant);
			sb.append(",Produit_existant=" + Produit_existant);
			sb.append(",Operation=" + Operation);
			sb.append(",Type_d_operation=" + Type_d_operation);
			sb.append(",Prix_unitaire=" + String.valueOf(Prix_unitaire));
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Remise=" + String.valueOf(Remise));
			sb.append(",Montant_de_la_remise_manuelle=" + String.valueOf(Montant_de_la_remise_manuelle));
			sb.append(",Total_final=" + String.valueOf(Total_final));
			sb.append(",Mois_et_annee_de_modification=" + Mois_et_annee_de_modification);
			sb.append(",Modifie_le=" + String.valueOf(Modifie_le));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputExcel_2Struct
			implements routines.system.IPersistableRow<after_tFileInputExcel_2Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];

		public String Client_Prospect;

		public String getClient_Prospect() {
			return this.Client_Prospect;
		}

		public String Sujet;

		public String getSujet() {
			return this.Sujet;
		}

		public String Acces;

		public String getAcces() {
			return this.Acces;
		}

		public String Type_d_acces;

		public String getType_d_acces() {
			return this.Type_d_acces;
		}

		public String Account_Manager__Client_Prospect;

		public String getAccount_Manager__Client_Prospect() {
			return this.Account_Manager__Client_Prospect;
		}

		public java.util.Date Cree_le;

		public java.util.Date getCree_le() {
			return this.Cree_le;
		}

		public String Mois_et_annee_de_creation;

		public String getMois_et_annee_de_creation() {
			return this.Mois_et_annee_de_creation;
		}

		public String Cree_par;

		public String getCree_par() {
			return this.Cree_par;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public String Proprietaire;

		public String getProprietaire() {
			return this.Proprietaire;
		}

		public String Phase_de_l_opportunite;

		public String getPhase_de_l_opportunite() {
			return this.Phase_de_l_opportunite;
		}

		public Float Valeur_ordering;

		public Float getValeur_ordering() {
			return this.Valeur_ordering;
		}

		public String Statut;

		public String getStatut() {
			return this.Statut;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		public String Tarifs;

		public String getTarifs() {
			return this.Tarifs;
		}

		public Float Montant_total;

		public Float getMontant_total() {
			return this.Montant_total;
		}

		public String Parent__Produit_existant;

		public String getParent__Produit_existant() {
			return this.Parent__Produit_existant;
		}

		public String Produit_existant;

		public String getProduit_existant() {
			return this.Produit_existant;
		}

		public String Operation;

		public String getOperation() {
			return this.Operation;
		}

		public String Type_d_operation;

		public String getType_d_operation() {
			return this.Type_d_operation;
		}

		public Float Prix_unitaire;

		public Float getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public Float Quantite;

		public Float getQuantite() {
			return this.Quantite;
		}

		public Float Remise;

		public Float getRemise() {
			return this.Remise;
		}

		public Float Montant_de_la_remise_manuelle;

		public Float getMontant_de_la_remise_manuelle() {
			return this.Montant_de_la_remise_manuelle;
		}

		public Float Total_final;

		public Float getTotal_final() {
			return this.Total_final;
		}

		public String Mois_et_annee_de_modification;

		public String getMois_et_annee_de_modification() {
			return this.Mois_et_annee_de_modification;
		}

		public java.util.Date Modifie_le;

		public java.util.Date getModifie_le() {
			return this.Modifie_le;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

					this.Sujet = readString(dis);

					this.Acces = readString(dis);

					this.Type_d_acces = readString(dis);

					this.Account_Manager__Client_Prospect = readString(dis);

					this.Cree_le = readDate(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Proprietaire = readString(dis);

					this.Phase_de_l_opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Valeur_ordering = null;
					} else {
						this.Valeur_ordering = dis.readFloat();
					}

					this.Statut = readString(dis);

					this.Staut_Final = readString(dis);

					this.Tarifs = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Montant_total = null;
					} else {
						this.Montant_total = dis.readFloat();
					}

					this.Parent__Produit_existant = readString(dis);

					this.Produit_existant = readString(dis);

					this.Operation = readString(dis);

					this.Type_d_operation = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise = null;
					} else {
						this.Remise = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_de_la_remise_manuelle = null;
					} else {
						this.Montant_de_la_remise_manuelle = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					this.Mois_et_annee_de_modification = readString(dis);

					this.Modifie_le = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

					this.Sujet = readString(dis);

					this.Acces = readString(dis);

					this.Type_d_acces = readString(dis);

					this.Account_Manager__Client_Prospect = readString(dis);

					this.Cree_le = readDate(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Proprietaire = readString(dis);

					this.Phase_de_l_opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Valeur_ordering = null;
					} else {
						this.Valeur_ordering = dis.readFloat();
					}

					this.Statut = readString(dis);

					this.Staut_Final = readString(dis);

					this.Tarifs = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Montant_total = null;
					} else {
						this.Montant_total = dis.readFloat();
					}

					this.Parent__Produit_existant = readString(dis);

					this.Produit_existant = readString(dis);

					this.Operation = readString(dis);

					this.Type_d_operation = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise = null;
					} else {
						this.Remise = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Montant_de_la_remise_manuelle = null;
					} else {
						this.Montant_de_la_remise_manuelle = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					this.Mois_et_annee_de_modification = readString(dis);

					this.Modifie_le = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Client_Prospect, dos);

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Acces, dos);

				// String

				writeString(this.Type_d_acces, dos);

				// String

				writeString(this.Account_Manager__Client_Prospect, dos);

				// java.util.Date

				writeDate(this.Cree_le, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Proprietaire, dos);

				// String

				writeString(this.Phase_de_l_opportunite, dos);

				// Float

				if (this.Valeur_ordering == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Valeur_ordering);
				}

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Tarifs, dos);

				// Float

				if (this.Montant_total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_total);
				}

				// String

				writeString(this.Parent__Produit_existant, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Operation, dos);

				// String

				writeString(this.Type_d_operation, dos);

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Remise == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise);
				}

				// Float

				if (this.Montant_de_la_remise_manuelle == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_de_la_remise_manuelle);
				}

				// Float

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// String

				writeString(this.Mois_et_annee_de_modification, dos);

				// java.util.Date

				writeDate(this.Modifie_le, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Client_Prospect, dos);

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Acces, dos);

				// String

				writeString(this.Type_d_acces, dos);

				// String

				writeString(this.Account_Manager__Client_Prospect, dos);

				// java.util.Date

				writeDate(this.Cree_le, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Proprietaire, dos);

				// String

				writeString(this.Phase_de_l_opportunite, dos);

				// Float

				if (this.Valeur_ordering == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Valeur_ordering);
				}

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Tarifs, dos);

				// Float

				if (this.Montant_total == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_total);
				}

				// String

				writeString(this.Parent__Produit_existant, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Operation, dos);

				// String

				writeString(this.Type_d_operation, dos);

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Remise == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise);
				}

				// Float

				if (this.Montant_de_la_remise_manuelle == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Montant_de_la_remise_manuelle);
				}

				// Float

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// String

				writeString(this.Mois_et_annee_de_modification, dos);

				// java.util.Date

				writeDate(this.Modifie_le, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Client_Prospect=" + Client_Prospect);
			sb.append(",Sujet=" + Sujet);
			sb.append(",Acces=" + Acces);
			sb.append(",Type_d_acces=" + Type_d_acces);
			sb.append(",Account_Manager__Client_Prospect=" + Account_Manager__Client_Prospect);
			sb.append(",Cree_le=" + String.valueOf(Cree_le));
			sb.append(",Mois_et_annee_de_creation=" + Mois_et_annee_de_creation);
			sb.append(",Cree_par=" + Cree_par);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Proprietaire=" + Proprietaire);
			sb.append(",Phase_de_l_opportunite=" + Phase_de_l_opportunite);
			sb.append(",Valeur_ordering=" + String.valueOf(Valeur_ordering));
			sb.append(",Statut=" + Statut);
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append(",Tarifs=" + Tarifs);
			sb.append(",Montant_total=" + String.valueOf(Montant_total));
			sb.append(",Parent__Produit_existant=" + Parent__Produit_existant);
			sb.append(",Produit_existant=" + Produit_existant);
			sb.append(",Operation=" + Operation);
			sb.append(",Type_d_operation=" + Type_d_operation);
			sb.append(",Prix_unitaire=" + String.valueOf(Prix_unitaire));
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Remise=" + String.valueOf(Remise));
			sb.append(",Montant_de_la_remise_manuelle=" + String.valueOf(Montant_de_la_remise_manuelle));
			sb.append(",Total_final=" + String.valueOf(Total_final));
			sb.append(",Mois_et_annee_de_modification=" + Mois_et_annee_de_modification);
			sb.append(",Modifie_le=" + String.valueOf(Modifie_le));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputExcel_2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tDBInput_1Process(globalMap);
				tDBInput_12Process(globalMap);
				tDBInput_13Process(globalMap);
				tDBInput_14Process(globalMap);
				tDBInput_15Process(globalMap);
				tDBInput_17Process(globalMap);
				tDBInput_18Process(globalMap);
				tDBInput_19Process(globalMap);

				row1Struct row1 = new row1Struct();
				CorrectionStruct Correction = new CorrectionStruct();
				orangeStruct orange = new orangeStruct();

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "orange");
				}

				int tos_count_tDBOutput_2 = 0;

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;
				int rejectedCount_tDBOutput_2 = 0;
				String dbschema_tDBOutput_2 = null;
				String tableName_tDBOutput_2 = null;
				boolean whetherReject_tDBOutput_2 = false;

				java.util.Calendar calendar_tDBOutput_2 = java.util.Calendar.getInstance();
				long year1_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_2;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_2 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_2 = null;
				String dbUser_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = "dbo";
				String driverClass_tDBOutput_2 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tDBOutput_2);
				String port_tDBOutput_2 = "1433";
				String dbname_tDBOutput_2 = "orange_DW__";
				String url_tDBOutput_2 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBOutput_2)) {
					url_tDBOutput_2 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_2)) {
					url_tDBOutput_2 += "//" + "orange_DW__";

				}
				url_tDBOutput_2 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_2 = "sa";

				final String decryptedPassword_tDBOutput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:Eicldj3gppcvt+J9/dVVRayf9J2VV0fzUk7Lu5x/0nZmUsXJFCw=");

				String dbPwd_tDBOutput_2 = decryptedPassword_tDBOutput_2;
				conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2, dbUser_tDBOutput_2,
						dbPwd_tDBOutput_2);

				resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);

				conn_tDBOutput_2.setAutoCommit(false);
				int commitEvery_tDBOutput_2 = 10000;
				int commitCounter_tDBOutput_2 = 0;

				int batchSize_tDBOutput_2 = 10000;
				int batchSizeCounter_tDBOutput_2 = 0;

				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = "fact_table_data";
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "].[" + "fact_table_data";
				}
				int count_tDBOutput_2 = 0;

				String insert_tDBOutput_2 = "INSERT INTO [" + tableName_tDBOutput_2
						+ "] ([Client_Prospect_id],[type_accés_id],[type_operation_id],[DIM_Date_id],[equipe_id],[sujet_id],[statu_final_id],[phase_de_l_opportunité_id],[membre_équipe_id],[Total_final],[Montant_total],[Quantite],[Prix_unitaire],[Remise],[Montant_de_la_remise_manuelle],[Valeur_ordering]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				ok_Hash.put("tMap_2", false);
				start_Hash.put("tMap_2", System.currentTimeMillis());

				currentComponent = "tMap_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Correction");
				}

				int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) globalMap
						.get("tHash_Lookup_row3"));

				row3Struct row3HashKey = new row3Struct();
				row3Struct row3Default = new row3Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) globalMap
						.get("tHash_Lookup_row7"));

				row7Struct row7HashKey = new row7Struct();
				row7Struct row7Default = new row7Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row11Struct> tHash_Lookup_row11 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row11Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row11Struct>) globalMap
						.get("tHash_Lookup_row11"));

				row11Struct row11HashKey = new row11Struct();
				row11Struct row11Default = new row11Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) globalMap
						.get("tHash_Lookup_row6"));

				row6Struct row6HashKey = new row6Struct();
				row6Struct row6Default = new row6Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) globalMap
						.get("tHash_Lookup_row8"));

				row8Struct row8HashKey = new row8Struct();
				row8Struct row8Default = new row8Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) globalMap
						.get("tHash_Lookup_row5"));

				row5Struct row5HashKey = new row5Struct();
				row5Struct row5Default = new row5Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) globalMap
						.get("tHash_Lookup_row4"));

				row4Struct row4HashKey = new row4Struct();
				row4Struct row4Default = new row4Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) globalMap
						.get("tHash_Lookup_row9"));

				row9Struct row9HashKey = new row9Struct();
				row9Struct row9Default = new row9Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				orangeStruct orange_tmp = new orangeStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				ok_Hash.put("tMap_3", false);
				start_Hash.put("tMap_3", System.currentTimeMillis());

				currentComponent = "tMap_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				CorrectionStruct Correction_tmp = new CorrectionStruct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tFileInputExcel_2 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_2", false);
				start_Hash.put("tFileInputExcel_2", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_2";

				int tos_count_tFileInputExcel_2 = 0;

				final String decryptedPassword_tFileInputExcel_2 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:X9k1eRg63YkxD1+BdL8/UDJPmfLc8o4DtU8FGg==");
				String password_tFileInputExcel_2 = decryptedPassword_tFileInputExcel_2;
				if (password_tFileInputExcel_2.isEmpty()) {
					password_tFileInputExcel_2 = null;
				}
				class RegexUtil_tFileInputExcel_2 {

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

						if (useRegex) {// this part process the regex issue

							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
								String sheetName = sheet.getSheetName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									if (sheet != null) {
										list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
									}
								}
							}

						} else {
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
								.getSheetAt(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}
				RegexUtil_tFileInputExcel_2 regexUtil_tFileInputExcel_2 = new RegexUtil_tFileInputExcel_2();

				Object source_tFileInputExcel_2 = "C:/Users/DELL/Downloads/Copie de Rapport Pipe et realisations DME Data et VoIP (2).xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_2 = null;

				if (source_tFileInputExcel_2 instanceof String) {
					workbook_tFileInputExcel_2 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_2), password_tFileInputExcel_2,
									true);
				} else if (source_tFileInputExcel_2 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_2 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_2, password_tFileInputExcel_2);
				} else {
					workbook_tFileInputExcel_2 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_2 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					sheetList_tFileInputExcel_2
							.addAll(regexUtil_tFileInputExcel_2.getSheets(workbook_tFileInputExcel_2, "Source", false));
					if (sheetList_tFileInputExcel_2.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_2 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_2 : sheetList_tFileInputExcel_2) {
						if (sheet_FilterNull_tFileInputExcel_2 != null
								&& sheetList_FilterNull_tFileInputExcel_2.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_2.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_2.add(sheet_FilterNull_tFileInputExcel_2);
						}
					}
					sheetList_tFileInputExcel_2 = sheetList_FilterNull_tFileInputExcel_2;
					if (sheetList_tFileInputExcel_2.size() > 0) {
						int nb_line_tFileInputExcel_2 = 0;

						int begin_line_tFileInputExcel_2 = 1;

						int footer_input_tFileInputExcel_2 = 0;

						int end_line_tFileInputExcel_2 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_2 : sheetList_tFileInputExcel_2) {
							end_line_tFileInputExcel_2 += (sheet_tFileInputExcel_2.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_2 -= footer_input_tFileInputExcel_2;
						int limit_tFileInputExcel_2 = -1;
						int start_column_tFileInputExcel_2 = 1 - 1;
						int end_column_tFileInputExcel_2 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_2 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_2 = sheetList_tFileInputExcel_2
								.get(0);
						int rowCount_tFileInputExcel_2 = 0;
						int sheetIndex_tFileInputExcel_2 = 0;
						int currentRows_tFileInputExcel_2 = (sheetList_tFileInputExcel_2.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_2 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_2 = df_tFileInputExcel_2.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_2 = begin_line_tFileInputExcel_2; i_tFileInputExcel_2 < end_line_tFileInputExcel_2; i_tFileInputExcel_2++) {

							int emptyColumnCount_tFileInputExcel_2 = 0;

							if (limit_tFileInputExcel_2 != -1 && nb_line_tFileInputExcel_2 >= limit_tFileInputExcel_2) {
								break;
							}

							while (i_tFileInputExcel_2 >= rowCount_tFileInputExcel_2 + currentRows_tFileInputExcel_2) {
								rowCount_tFileInputExcel_2 += currentRows_tFileInputExcel_2;
								sheet_tFileInputExcel_2 = sheetList_tFileInputExcel_2
										.get(++sheetIndex_tFileInputExcel_2);
								currentRows_tFileInputExcel_2 = (sheet_tFileInputExcel_2.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_2_CURRENT_SHEET", sheet_tFileInputExcel_2.getSheetName());
							if (rowCount_tFileInputExcel_2 <= i_tFileInputExcel_2) {
								row_tFileInputExcel_2 = sheet_tFileInputExcel_2
										.getRow(i_tFileInputExcel_2 - rowCount_tFileInputExcel_2);
							}
							row1 = null;
							int tempRowLength_tFileInputExcel_2 = 27;

							int columnIndex_tFileInputExcel_2 = 0;

							String[] temp_row_tFileInputExcel_2 = new String[tempRowLength_tFileInputExcel_2];
							int excel_end_column_tFileInputExcel_2;
							if (row_tFileInputExcel_2 == null) {
								excel_end_column_tFileInputExcel_2 = 0;
							} else {
								excel_end_column_tFileInputExcel_2 = row_tFileInputExcel_2.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_2;
							if (end_column_tFileInputExcel_2 == -1) {
								actual_end_column_tFileInputExcel_2 = excel_end_column_tFileInputExcel_2;
							} else {
								actual_end_column_tFileInputExcel_2 = end_column_tFileInputExcel_2 > excel_end_column_tFileInputExcel_2
										? excel_end_column_tFileInputExcel_2
										: end_column_tFileInputExcel_2;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_2 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_2; i++) {
								if (i + start_column_tFileInputExcel_2 < actual_end_column_tFileInputExcel_2) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_2 = row_tFileInputExcel_2
											.getCell(i + start_column_tFileInputExcel_2);
									if (cell_tFileInputExcel_2 != null) {
										switch (cell_tFileInputExcel_2.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_2[i] = cell_tFileInputExcel_2
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_2)) {
												temp_row_tFileInputExcel_2[i] = cell_tFileInputExcel_2
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_2[i] = df_tFileInputExcel_2
														.format(cell_tFileInputExcel_2.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_2[i] = String
													.valueOf(cell_tFileInputExcel_2.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_2.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_2[i] = cell_tFileInputExcel_2
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_2)) {
													temp_row_tFileInputExcel_2[i] = cell_tFileInputExcel_2
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_2 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_2.getNumericCellValue());
													temp_row_tFileInputExcel_2[i] = ne_tFileInputExcel_2
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_2[i] = String
														.valueOf(cell_tFileInputExcel_2.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_2[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_2[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_2[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_2[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_2 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_2 = -1;
							String curColName_tFileInputExcel_2 = "";
							try {
								columnIndex_tFileInputExcel_2 = 0;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Client_Prospect";

									row1.Client_Prospect = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Client_Prospect = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 1;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Sujet";

									row1.Sujet = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Sujet = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 2;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Acces";

									row1.Acces = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Acces = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 3;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Type_d_acces";

									row1.Type_d_acces = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Type_d_acces = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 4;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Account_Manager__Client_Prospect";

									row1.Account_Manager__Client_Prospect = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Account_Manager__Client_Prospect = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 5;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Cree_le";

									if (5 < actual_end_column_tFileInputExcel_2) {
										try {
											if (row_tFileInputExcel_2
													.getCell(columnIndex_tFileInputExcel_2
															+ start_column_tFileInputExcel_2)
													.getCellType() == org.apache.poi.ss.usermodel.CellType.NUMERIC
													&& org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(
															row_tFileInputExcel_2.getCell(columnIndex_tFileInputExcel_2
																	+ start_column_tFileInputExcel_2))) {
												row1.Cree_le = row_tFileInputExcel_2.getCell(
														columnIndex_tFileInputExcel_2 + start_column_tFileInputExcel_2)
														.getDateCellValue();
											} else {
												java.util.Date tempDate_tFileInputExcel_2 = ParserUtils.parseTo_Date(
														temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2],
														"dd-MM-yyyy");
												if (tempDate_tFileInputExcel_2
														.after((new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS"))
																.parse("9999/12/31 23:59:59.999"))
														|| tempDate_tFileInputExcel_2
																.before((new SimpleDateFormat("yyyy/MM/dd"))
																		.parse("1900/01/01"))) {
													throw new RuntimeException("The cell format is not Date in ( Row. "
															+ (nb_line_tFileInputExcel_2 + 1) + " and ColumnNum. "
															+ curColNum_tFileInputExcel_2 + " )");
												} else {
													row1.Cree_le = tempDate_tFileInputExcel_2;
												}
											}
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_2_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_2 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_2 + " )");
										}
									}

								} else {
									row1.Cree_le = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 6;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Mois_et_annee_de_creation";

									row1.Mois_et_annee_de_creation = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Mois_et_annee_de_creation = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 7;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Cree_par";

									row1.Cree_par = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Cree_par = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 8;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Equipe";

									row1.Equipe = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Equipe = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 9;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Proprietaire";

									row1.Proprietaire = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Proprietaire = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 10;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Phase_de_l_opportunite";

									row1.Phase_de_l_opportunite = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Phase_de_l_opportunite = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 11;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Valeur_ordering";

									row1.Valeur_ordering = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
											'.' == decimalChar_tFileInputExcel_2 ? null
													: decimalChar_tFileInputExcel_2));
								} else {
									row1.Valeur_ordering = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 12;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Statut";

									row1.Statut = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Statut = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 13;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Staut_Final";

									row1.Staut_Final = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Staut_Final = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 14;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Tarifs";

									row1.Tarifs = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Tarifs = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 15;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Montant_total";

									row1.Montant_total = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
											'.' == decimalChar_tFileInputExcel_2 ? null
													: decimalChar_tFileInputExcel_2));
								} else {
									row1.Montant_total = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 16;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Parent__Produit_existant";

									row1.Parent__Produit_existant = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Parent__Produit_existant = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 17;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Produit_existant";

									row1.Produit_existant = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Produit_existant = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 18;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Operation";

									row1.Operation = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Operation = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 19;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Type_d_operation";

									row1.Type_d_operation = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Type_d_operation = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 20;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Prix_unitaire";

									row1.Prix_unitaire = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
											'.' == decimalChar_tFileInputExcel_2 ? null
													: decimalChar_tFileInputExcel_2));
								} else {
									row1.Prix_unitaire = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 21;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Quantite";

									row1.Quantite = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
											'.' == decimalChar_tFileInputExcel_2 ? null
													: decimalChar_tFileInputExcel_2));
								} else {
									row1.Quantite = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 22;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Remise";

									row1.Remise = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
											'.' == decimalChar_tFileInputExcel_2 ? null
													: decimalChar_tFileInputExcel_2));
								} else {
									row1.Remise = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 23;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Montant_de_la_remise_manuelle";

									row1.Montant_de_la_remise_manuelle = ParserUtils.parseTo_Float(ParserUtils
											.parseTo_Number(temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2],
													null, '.' == decimalChar_tFileInputExcel_2 ? null
															: decimalChar_tFileInputExcel_2));
								} else {
									row1.Montant_de_la_remise_manuelle = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 24;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Total_final";

									row1.Total_final = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2], null,
											'.' == decimalChar_tFileInputExcel_2 ? null
													: decimalChar_tFileInputExcel_2));
								} else {
									row1.Total_final = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 25;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Mois_et_annee_de_modification";

									row1.Mois_et_annee_de_modification = temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2];
								} else {
									row1.Mois_et_annee_de_modification = null;
									emptyColumnCount_tFileInputExcel_2++;
								}
								columnIndex_tFileInputExcel_2 = 26;

								if (temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2].length() > 0) {
									curColNum_tFileInputExcel_2 = columnIndex_tFileInputExcel_2
											+ start_column_tFileInputExcel_2 + 1;
									curColName_tFileInputExcel_2 = "Modifie_le";

									if (26 < actual_end_column_tFileInputExcel_2) {
										try {
											if (row_tFileInputExcel_2
													.getCell(columnIndex_tFileInputExcel_2
															+ start_column_tFileInputExcel_2)
													.getCellType() == org.apache.poi.ss.usermodel.CellType.NUMERIC
													&& org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(
															row_tFileInputExcel_2.getCell(columnIndex_tFileInputExcel_2
																	+ start_column_tFileInputExcel_2))) {
												row1.Modifie_le = row_tFileInputExcel_2.getCell(
														columnIndex_tFileInputExcel_2 + start_column_tFileInputExcel_2)
														.getDateCellValue();
											} else {
												java.util.Date tempDate_tFileInputExcel_2 = ParserUtils.parseTo_Date(
														temp_row_tFileInputExcel_2[columnIndex_tFileInputExcel_2],
														"dd-MM-yyyy");
												if (tempDate_tFileInputExcel_2
														.after((new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS"))
																.parse("9999/12/31 23:59:59.999"))
														|| tempDate_tFileInputExcel_2
																.before((new SimpleDateFormat("yyyy/MM/dd"))
																		.parse("1900/01/01"))) {
													throw new RuntimeException("The cell format is not Date in ( Row. "
															+ (nb_line_tFileInputExcel_2 + 1) + " and ColumnNum. "
															+ curColNum_tFileInputExcel_2 + " )");
												} else {
													row1.Modifie_le = tempDate_tFileInputExcel_2;
												}
											}
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_2_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_2 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_2 + " )");
										}
									}

								} else {
									row1.Modifie_le = null;
									emptyColumnCount_tFileInputExcel_2++;
								}

								nb_line_tFileInputExcel_2++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_2_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_2 = true;
								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputExcel_2 begin ] stop
							 */

							/**
							 * [tFileInputExcel_2 main ] start
							 */

							currentComponent = "tFileInputExcel_2";

							tos_count_tFileInputExcel_2++;

							/**
							 * [tFileInputExcel_2 main ] stop
							 */

							/**
							 * [tFileInputExcel_2 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_2";

							/**
							 * [tFileInputExcel_2 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tMap_3 main ] start
								 */

								currentComponent = "tMap_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row1"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_3 = false;
								boolean mainRowRejected_tMap_3 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
									// ###############################
									// # Output tables

									Correction = null;

// # Output table : 'Correction'
									Correction_tmp.Client_Prospect = row1.Client_Prospect;
									Correction_tmp.Sujet = row1.Sujet;
									Correction_tmp.Acces = row1.Acces;
									Correction_tmp.Type_d_acces = row1.Type_d_acces;
									Correction_tmp.Account_Manager__Client_Prospect = row1.Account_Manager__Client_Prospect;
									Correction_tmp.Cree_le = row1.Cree_le;
									Correction_tmp.Mois_et_annee_de_creation = row1.Mois_et_annee_de_creation;
									Correction_tmp.Cree_par = StringHandling.UPCASE(row1.Cree_par);
									Correction_tmp.Equipe = row1.Equipe;
									Correction_tmp.Proprietaire = row1.Proprietaire;
									Correction_tmp.Phase_de_l_opportunite = row1.Phase_de_l_opportunite;
									Correction_tmp.Valeur_ordering = row1.Valeur_ordering;
									Correction_tmp.Statut = row1.Statut;
									Correction_tmp.Staut_Final = row1.Staut_Final;
									Correction_tmp.Tarifs = row1.Tarifs;
									Correction_tmp.Montant_total = row1.Montant_total;
									Correction_tmp.Parent__Produit_existant = row1.Parent__Produit_existant;
									Correction_tmp.Produit_existant = row1.Produit_existant;
									Correction_tmp.Operation = row1.Operation;
									Correction_tmp.Type_d_operation = row1.Type_d_operation;
									Correction_tmp.Prix_unitaire = row1.Prix_unitaire;
									Correction_tmp.Quantite = row1.Quantite;
									Correction_tmp.Remise = row1.Remise;
									Correction_tmp.Montant_de_la_remise_manuelle = row1.Montant_de_la_remise_manuelle;
									Correction_tmp.Total_final = row1.Total_final;
									Correction_tmp.Modifie_le = row1.Modifie_le;
									Correction = Correction_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_3 = false;

								tos_count_tMap_3++;

								/**
								 * [tMap_3 main ] stop
								 */

								/**
								 * [tMap_3 process_data_begin ] start
								 */

								currentComponent = "tMap_3";

								/**
								 * [tMap_3 process_data_begin ] stop
								 */
// Start of branch "Correction"
								if (Correction != null) {

									/**
									 * [tMap_2 main ] start
									 */

									currentComponent = "tMap_2";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "Correction"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_2 = false;
									boolean mainRowRejected_tMap_2 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row3"
									///////////////////////////////////////////////

									boolean forceLooprow3 = false;

									row3Struct row3ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row3HashKey.Phase_de_l_opportunite = Correction.Phase_de_l_opportunite;

										row3HashKey.hashCodeDirty = true;

										tHash_Lookup_row3.lookup(row3HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3'
										// and it contains more one result from keys : row3.Phase_de_l_opportunite = '"
										// + row3HashKey.Phase_de_l_opportunite + "'");
									} // G 071

									row3Struct row3 = null;

									row3Struct fromLookup_row3 = null;
									row3 = row3Default;

									if (tHash_Lookup_row3 != null && tHash_Lookup_row3.hasNext()) { // G 099

										fromLookup_row3 = tHash_Lookup_row3.next();

									} // G 099

									if (fromLookup_row3 != null) {
										row3 = fromLookup_row3;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row7"
									///////////////////////////////////////////////

									boolean forceLooprow7 = false;

									row7Struct row7ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row7HashKey.Staut_Final = Correction.Staut_Final;

										row7HashKey.hashCodeDirty = true;

										tHash_Lookup_row7.lookup(row7HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row7 != null && tHash_Lookup_row7.getCount(row7HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row7'
										// and it contains more one result from keys : row7.Staut_Final = '" +
										// row7HashKey.Staut_Final + "'");
									} // G 071

									row7Struct row7 = null;

									row7Struct fromLookup_row7 = null;
									row7 = row7Default;

									if (tHash_Lookup_row7 != null && tHash_Lookup_row7.hasNext()) { // G 099

										fromLookup_row7 = tHash_Lookup_row7.next();

									} // G 099

									if (fromLookup_row7 != null) {
										row7 = fromLookup_row7;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row11"
									///////////////////////////////////////////////

									boolean forceLooprow11 = false;

									row11Struct row11ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row11HashKey.Type_d_operation = Correction.Type_d_operation;

										row11HashKey.hashCodeDirty = true;

										tHash_Lookup_row11.lookup(row11HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row11 != null && tHash_Lookup_row11.getCount(row11HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row11' and it contains more one result from keys : row11.Type_d_operation =
										// '" + row11HashKey.Type_d_operation + "'");
									} // G 071

									row11Struct row11 = null;

									row11Struct fromLookup_row11 = null;
									row11 = row11Default;

									if (tHash_Lookup_row11 != null && tHash_Lookup_row11.hasNext()) { // G 099

										fromLookup_row11 = tHash_Lookup_row11.next();

									} // G 099

									if (fromLookup_row11 != null) {
										row11 = fromLookup_row11;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row6"
									///////////////////////////////////////////////

									boolean forceLooprow6 = false;

									row6Struct row6ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row6HashKey.Equipe = Correction.Equipe;

										row6HashKey.hashCodeDirty = true;

										tHash_Lookup_row6.lookup(row6HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row6 != null && tHash_Lookup_row6.getCount(row6HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row6'
										// and it contains more one result from keys : row6.Equipe = '" +
										// row6HashKey.Equipe + "'");
									} // G 071

									row6Struct row6 = null;

									row6Struct fromLookup_row6 = null;
									row6 = row6Default;

									if (tHash_Lookup_row6 != null && tHash_Lookup_row6.hasNext()) { // G 099

										fromLookup_row6 = tHash_Lookup_row6.next();

									} // G 099

									if (fromLookup_row6 != null) {
										row6 = fromLookup_row6;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row8"
									///////////////////////////////////////////////

									boolean forceLooprow8 = false;

									row8Struct row8ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row8HashKey.Sujet = Correction.Sujet;

										row8HashKey.hashCodeDirty = true;

										tHash_Lookup_row8.lookup(row8HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row8 != null && tHash_Lookup_row8.getCount(row8HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row8'
										// and it contains more one result from keys : row8.Sujet = '" +
										// row8HashKey.Sujet + "'");
									} // G 071

									row8Struct row8 = null;

									row8Struct fromLookup_row8 = null;
									row8 = row8Default;

									if (tHash_Lookup_row8 != null && tHash_Lookup_row8.hasNext()) { // G 099

										fromLookup_row8 = tHash_Lookup_row8.next();

									} // G 099

									if (fromLookup_row8 != null) {
										row8 = fromLookup_row8;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row5"
									///////////////////////////////////////////////

									boolean forceLooprow5 = false;

									row5Struct row5ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row5HashKey.Client_Prospect = Correction.Client_Prospect;

										row5HashKey.hashCodeDirty = true;

										tHash_Lookup_row5.lookup(row5HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row5 != null && tHash_Lookup_row5.getCount(row5HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row5'
										// and it contains more one result from keys : row5.Client_Prospect = '" +
										// row5HashKey.Client_Prospect + "'");
									} // G 071

									row5Struct row5 = null;

									row5Struct fromLookup_row5 = null;
									row5 = row5Default;

									if (tHash_Lookup_row5 != null && tHash_Lookup_row5.hasNext()) { // G 099

										fromLookup_row5 = tHash_Lookup_row5.next();

									} // G 099

									if (fromLookup_row5 != null) {
										row5 = fromLookup_row5;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row4"
									///////////////////////////////////////////////

									boolean forceLooprow4 = false;

									row4Struct row4ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row4HashKey.Type_d_acces = Correction.Type_d_acces;

										row4HashKey.hashCodeDirty = true;

										tHash_Lookup_row4.lookup(row4HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4'
										// and it contains more one result from keys : row4.Type_d_acces = '" +
										// row4HashKey.Type_d_acces + "'");
									} // G 071

									row4Struct row4 = null;

									row4Struct fromLookup_row4 = null;
									row4 = row4Default;

									if (tHash_Lookup_row4 != null && tHash_Lookup_row4.hasNext()) { // G 099

										fromLookup_row4 = tHash_Lookup_row4.next();

									} // G 099

									if (fromLookup_row4 != null) {
										row4 = fromLookup_row4;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row9"
									///////////////////////////////////////////////

									boolean forceLooprow9 = false;

									row9Struct row9ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row9HashKey.Account_Manager__Client_Prospect = Correction.Cree_par;

										row9HashKey.hashCodeDirty = true;

										tHash_Lookup_row9.lookup(row9HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row9 != null && tHash_Lookup_row9.getCount(row9HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row9'
										// and it contains more one result from keys :
										// row9.Account_Manager__Client_Prospect = '" +
										// row9HashKey.Account_Manager__Client_Prospect + "'");
									} // G 071

									row9Struct row9 = null;

									row9Struct fromLookup_row9 = null;
									row9 = row9Default;

									if (tHash_Lookup_row9 != null && tHash_Lookup_row9.hasNext()) { // G 099

										fromLookup_row9 = tHash_Lookup_row9.next();

									} // G 099

									if (fromLookup_row9 != null) {
										row9 = fromLookup_row9;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
										// ###############################
										// # Output tables

										orange = null;

// # Output table : 'orange'
										orange_tmp.Client_Prospect_id = row5.Client_Prospect_id;
										orange_tmp.type_acces_id = row4.type_acces_id;
										orange_tmp.type_operation_id = row11.type_operation_id;
										orange_tmp.DIM_Date_id = Correction.Modifie_le;
										orange_tmp.equipe_id = row6.equipe_id;
										orange_tmp.sujet_id = row8.sujet_id;
										orange_tmp.statu_final_id = row7.statu_final_id;
										orange_tmp.phase_de_l_opportunite_id = row3.phase_de_l_opportunite_id;
										orange_tmp.membre_equipe_id = row9.membre_equipe_id;
										orange_tmp.Total_final = Correction.Total_final;
										orange_tmp.Montant_total = Correction.Montant_total;
										orange_tmp.Quantite = Correction.Quantite;
										orange_tmp.Prix_unitaire = Correction.Prix_unitaire;
										orange_tmp.Remise = Correction.Remise;
										orange_tmp.Montant_de_la_remise_manuelle = Correction.Montant_de_la_remise_manuelle;
										orange_tmp.Valeur_ordering = Correction.Valeur_ordering;
										orange = orange_tmp;
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_2 = false;

									tos_count_tMap_2++;

									/**
									 * [tMap_2 main ] stop
									 */

									/**
									 * [tMap_2 process_data_begin ] start
									 */

									currentComponent = "tMap_2";

									/**
									 * [tMap_2 process_data_begin ] stop
									 */
// Start of branch "orange"
									if (orange != null) {

										/**
										 * [tDBOutput_2 main ] start
										 */

										currentComponent = "tDBOutput_2";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "orange"

											);
										}

										whetherReject_tDBOutput_2 = false;
										if (orange.Client_Prospect_id == null) {
											pstmt_tDBOutput_2.setNull(1, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(1, orange.Client_Prospect_id);
										}

										if (orange.type_acces_id == null) {
											pstmt_tDBOutput_2.setNull(2, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(2, orange.type_acces_id);
										}

										if (orange.type_operation_id == null) {
											pstmt_tDBOutput_2.setNull(3, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(3, orange.type_operation_id);
										}

										if (orange.DIM_Date_id != null) {
											pstmt_tDBOutput_2.setTimestamp(4,
													new java.sql.Timestamp(orange.DIM_Date_id.getTime()));
										} else {
											pstmt_tDBOutput_2.setNull(4, java.sql.Types.TIMESTAMP);
										}

										if (orange.equipe_id == null) {
											pstmt_tDBOutput_2.setNull(5, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(5, orange.equipe_id);
										}

										if (orange.sujet_id == null) {
											pstmt_tDBOutput_2.setNull(6, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(6, orange.sujet_id);
										}

										if (orange.statu_final_id == null) {
											pstmt_tDBOutput_2.setNull(7, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(7, orange.statu_final_id);
										}

										if (orange.phase_de_l_opportunite_id == null) {
											pstmt_tDBOutput_2.setNull(8, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(8, orange.phase_de_l_opportunite_id);
										}

										if (orange.membre_equipe_id == null) {
											pstmt_tDBOutput_2.setNull(9, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(9, orange.membre_equipe_id);
										}

										if (orange.Total_final == null) {
											pstmt_tDBOutput_2.setNull(10, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(10, orange.Total_final);
										}

										if (orange.Montant_total == null) {
											pstmt_tDBOutput_2.setNull(11, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(11, orange.Montant_total);
										}

										if (orange.Quantite == null) {
											pstmt_tDBOutput_2.setNull(12, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(12, orange.Quantite);
										}

										if (orange.Prix_unitaire == null) {
											pstmt_tDBOutput_2.setNull(13, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(13, orange.Prix_unitaire);
										}

										if (orange.Remise == null) {
											pstmt_tDBOutput_2.setNull(14, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(14, orange.Remise);
										}

										if (orange.Montant_de_la_remise_manuelle == null) {
											pstmt_tDBOutput_2.setNull(15, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(15, orange.Montant_de_la_remise_manuelle);
										}

										if (orange.Valeur_ordering == null) {
											pstmt_tDBOutput_2.setNull(16, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(16, orange.Valeur_ordering);
										}

										pstmt_tDBOutput_2.addBatch();
										nb_line_tDBOutput_2++;

										batchSizeCounter_tDBOutput_2++;

										////////// batch execute by batch size///////
										class LimitBytesHelper_tDBOutput_2 {
											public int limitBytePart1(int counter,
													java.sql.PreparedStatement pstmt_tDBOutput_2) throws Exception {
												try {

													for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
														if (countEach_tDBOutput_2 == -2
																|| countEach_tDBOutput_2 == -3) {
															break;
														}
														counter += countEach_tDBOutput_2;
													}

												} catch (java.sql.BatchUpdateException e) {
													globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

													int countSum_tDBOutput_2 = 0;
													for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
														counter += (countEach_tDBOutput_2 < 0 ? 0
																: countEach_tDBOutput_2);
													}

													System.err.println(e.getMessage());

												}
												return counter;
											}

											public int limitBytePart2(int counter,
													java.sql.PreparedStatement pstmt_tDBOutput_2) throws Exception {
												try {

													for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
														if (countEach_tDBOutput_2 == -2
																|| countEach_tDBOutput_2 == -3) {
															break;
														}
														counter += countEach_tDBOutput_2;
													}

												} catch (java.sql.BatchUpdateException e) {
													globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

													for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
														counter += (countEach_tDBOutput_2 < 0 ? 0
																: countEach_tDBOutput_2);
													}

													System.err.println(e.getMessage());

												}
												return counter;
											}
										}
										if ((batchSize_tDBOutput_2 > 0)
												&& (batchSize_tDBOutput_2 <= batchSizeCounter_tDBOutput_2)) {

											insertedCount_tDBOutput_2 = new LimitBytesHelper_tDBOutput_2()
													.limitBytePart1(insertedCount_tDBOutput_2, pstmt_tDBOutput_2);
											rowsToCommitCount_tDBOutput_2 = insertedCount_tDBOutput_2;

											batchSizeCounter_tDBOutput_2 = 0;
										}

										//////////// commit every////////////

										commitCounter_tDBOutput_2++;
										if (commitEvery_tDBOutput_2 <= commitCounter_tDBOutput_2) {
											if ((batchSize_tDBOutput_2 > 0) && (batchSizeCounter_tDBOutput_2 > 0)) {

												insertedCount_tDBOutput_2 = new LimitBytesHelper_tDBOutput_2()
														.limitBytePart1(insertedCount_tDBOutput_2, pstmt_tDBOutput_2);

												batchSizeCounter_tDBOutput_2 = 0;
											}
											if (rowsToCommitCount_tDBOutput_2 != 0) {

											}
											conn_tDBOutput_2.commit();
											if (rowsToCommitCount_tDBOutput_2 != 0) {

												rowsToCommitCount_tDBOutput_2 = 0;
											}
											commitCounter_tDBOutput_2 = 0;
										}

										tos_count_tDBOutput_2++;

										/**
										 * [tDBOutput_2 main ] stop
										 */

										/**
										 * [tDBOutput_2 process_data_begin ] start
										 */

										currentComponent = "tDBOutput_2";

										/**
										 * [tDBOutput_2 process_data_begin ] stop
										 */

										/**
										 * [tDBOutput_2 process_data_end ] start
										 */

										currentComponent = "tDBOutput_2";

										/**
										 * [tDBOutput_2 process_data_end ] stop
										 */

									} // End of branch "orange"

									/**
									 * [tMap_2 process_data_end ] start
									 */

									currentComponent = "tMap_2";

									/**
									 * [tMap_2 process_data_end ] stop
									 */

								} // End of branch "Correction"

								/**
								 * [tMap_3 process_data_end ] start
								 */

								currentComponent = "tMap_3";

								/**
								 * [tMap_3 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputExcel_2 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_2";

							/**
							 * [tFileInputExcel_2 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_2 end ] start
							 */

							currentComponent = "tFileInputExcel_2";

						}

						globalMap.put("tFileInputExcel_2_NB_LINE", nb_line_tFileInputExcel_2);

					}

				} finally {

					if (!(source_tFileInputExcel_2 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_2.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_2", true);
				end_Hash.put("tFileInputExcel_2", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_2 end ] stop
				 */

				/**
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row3 != null) {
					tHash_Lookup_row3.endGet();
				}
				globalMap.remove("tHash_Lookup_row3");

				if (tHash_Lookup_row7 != null) {
					tHash_Lookup_row7.endGet();
				}
				globalMap.remove("tHash_Lookup_row7");

				if (tHash_Lookup_row11 != null) {
					tHash_Lookup_row11.endGet();
				}
				globalMap.remove("tHash_Lookup_row11");

				if (tHash_Lookup_row6 != null) {
					tHash_Lookup_row6.endGet();
				}
				globalMap.remove("tHash_Lookup_row6");

				if (tHash_Lookup_row8 != null) {
					tHash_Lookup_row8.endGet();
				}
				globalMap.remove("tHash_Lookup_row8");

				if (tHash_Lookup_row5 != null) {
					tHash_Lookup_row5.endGet();
				}
				globalMap.remove("tHash_Lookup_row5");

				if (tHash_Lookup_row4 != null) {
					tHash_Lookup_row4.endGet();
				}
				globalMap.remove("tHash_Lookup_row4");

				if (tHash_Lookup_row9 != null) {
					tHash_Lookup_row9.endGet();
				}
				globalMap.remove("tHash_Lookup_row9");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Correction");
				}

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					int countSum_tDBOutput_2 = 0;
					if (pstmt_tDBOutput_2 != null && batchSizeCounter_tDBOutput_2 > 0) {

						for (int countEach_tDBOutput_2 : pstmt_tDBOutput_2.executeBatch()) {
							if (countEach_tDBOutput_2 == -2 || countEach_tDBOutput_2 == -3) {
								break;
							}
							countSum_tDBOutput_2 += countEach_tDBOutput_2;
						}
						rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

					}

					insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_2 = 0;
					for (int countEach_tDBOutput_2 : e.getUpdateCounts()) {
						countSum_tDBOutput_2 += (countEach_tDBOutput_2 < 0 ? 0 : countEach_tDBOutput_2);
					}
					rowsToCommitCount_tDBOutput_2 += countSum_tDBOutput_2;

					insertedCount_tDBOutput_2 += countSum_tDBOutput_2;

					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_2 != null) {

					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");

				}
				resourceMap.put("statementClosed_tDBOutput_2", true);
				if (rowsToCommitCount_tDBOutput_2 != 0) {

				}
				conn_tDBOutput_2.commit();
				if (rowsToCommitCount_tDBOutput_2 != 0) {

					rowsToCommitCount_tDBOutput_2 = 0;
				}
				commitCounter_tDBOutput_2 = 0;
				conn_tDBOutput_2.close();
				resourceMap.put("finish_tDBOutput_2", true);

				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "orange");
				}

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row9");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row11");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row8");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row7");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row6");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row5");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row4");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row3");

			try {

				/**
				 * [tFileInputExcel_2 finally ] start
				 */

				currentComponent = "tFileInputExcel_2";

				/**
				 * [tFileInputExcel_2 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
						if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_2")) != null) {
							pstmtToClose_tDBOutput_2.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_2") == null) {
						java.sql.Connection ctn_tDBOutput_2 = null;
						if ((ctn_tDBOutput_2 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_2")) != null) {
							try {
								ctn_tDBOutput_2.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_2) {
								String errorMessage_tDBOutput_2 = "failed to close the connection in tDBOutput_2 :"
										+ sqlEx_tDBOutput_2.getMessage();
								System.err.println(errorMessage_tDBOutput_2);
							}
						}
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_2_SUBPROCESS_STATE", 1);
	}

	public static class row9Struct implements routines.system.IPersistableComparableLookupRow<row9Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int membre_equipe_id;

		public int getMembre_equipe_id() {
			return this.membre_equipe_id;
		}

		public String Account_Manager__Client_Prospect;

		public String getAccount_Manager__Client_Prospect() {
			return this.Account_Manager__Client_Prospect;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Account_Manager__Client_Prospect == null) ? 0
						: this.Account_Manager__Client_Prospect.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row9Struct other = (row9Struct) obj;

			if (this.Account_Manager__Client_Prospect == null) {
				if (other.Account_Manager__Client_Prospect != null)
					return false;

			} else if (!this.Account_Manager__Client_Prospect.equals(other.Account_Manager__Client_Prospect))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other.membre_equipe_id = this.membre_equipe_id;
			other.Account_Manager__Client_Prospect = this.Account_Manager__Client_Prospect;

		}

		public void copyKeysDataTo(row9Struct other) {

			other.Account_Manager__Client_Prospect = this.Account_Manager__Client_Prospect;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Account_Manager__Client_Prospect = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Account_Manager__Client_Prospect = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Account_Manager__Client_Prospect, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Account_Manager__Client_Prospect, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.membre_equipe_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.membre_equipe_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.membre_equipe_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.membre_equipe_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("membre_equipe_id=" + String.valueOf(membre_equipe_id));
			sb.append(",Account_Manager__Client_Prospect=" + Account_Manager__Client_Prospect);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Account_Manager__Client_Prospect,
					other.Account_Manager__Client_Prospect);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row9Struct row9 = new row9Struct();

				/**
				 * [tAdvancedHash_row9 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row9", false);
				start_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row9";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
				}

				int tos_count_tAdvancedHash_row9 = 0;

				// connection name:row9
				// source node:tDBInput_1 - inputs:(after_tFileInputExcel_2) outputs:(row9,row9)
				// | target node:tAdvancedHash_row9 - inputs:(row9) outputs:()
				// linked node: tMap_2 -
				// inputs:(Correction,row9,row11,row8,row7,row6,row5,row4,row3) outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row9 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row9Struct>getLookup(matchingModeEnum_row9);

				globalMap.put("tHash_Lookup_row9", tHash_Lookup_row9);

				/**
				 * [tAdvancedHash_row9 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				int tos_count_tDBInput_1 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_1 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_1 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_1 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_1, talendToDBArray_tDBInput_1);
				int nb_line_tDBInput_1 = 0;
				java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "sa";

				final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:Mrk9NePmnZAh9EhJeKxnX8uJtr0vZjlPRszA7zMQngTVzSQ/MgU=");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String port_tDBInput_1 = "1433";
				String dbname_tDBInput_1 = "orange_DW__";
				String url_tDBInput_1 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_1)) {
					url_tDBInput_1 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_1)) {
					url_tDBInput_1 += "//" + "orange_DW__";
				}
				url_tDBInput_1 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_1 = "dbo";

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "SELECT dbo.Dim_membre.\"membre_équipe_id\",\n		dbo.Dim_membre.Account_Manager__Client_Prospect\nFROM	dbo.Dim_membre";

				globalMap.put("tDBInput_1_QUERY", dbquery_tDBInput_1);
				java.sql.ResultSet rs_tDBInput_1 = null;

				try {
					rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
					java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
					int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

					String tmpContent_tDBInput_1 = null;

					while (rs_tDBInput_1.next()) {
						nb_line_tDBInput_1++;

						if (colQtyInRs_tDBInput_1 < 1) {
							row9.membre_equipe_id = 0;
						} else {

							row9.membre_equipe_id = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row9.Account_Manager__Client_Prospect = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row9.Account_Manager__Client_Prospect = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row9.Account_Manager__Client_Prospect = tmpContent_tDBInput_1;
								}
							} else {
								row9.Account_Manager__Client_Prospect = null;
							}
						}

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row9 main ] start
						 */

						currentComponent = "tAdvancedHash_row9";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row9"

							);
						}

						row9Struct row9_HashRow = new row9Struct();

						row9_HashRow.membre_equipe_id = row9.membre_equipe_id;

						row9_HashRow.Account_Manager__Client_Prospect = row9.Account_Manager__Client_Prospect;

						tHash_Lookup_row9.put(row9_HashRow);

						tos_count_tAdvancedHash_row9++;

						/**
						 * [tAdvancedHash_row9 main ] stop
						 */

						/**
						 * [tAdvancedHash_row9 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row9";

						/**
						 * [tAdvancedHash_row9 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row9 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row9";

						/**
						 * [tAdvancedHash_row9 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

					}
				} finally {
					if (rs_tDBInput_1 != null) {
						rs_tDBInput_1.close();
					}
					if (stmt_tDBInput_1 != null) {
						stmt_tDBInput_1.close();
					}
					if (conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {

						conn_tDBInput_1.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_1_NB_LINE", nb_line_tDBInput_1);

				ok_Hash.put("tDBInput_1", true);
				end_Hash.put("tDBInput_1", System.currentTimeMillis());

				/**
				 * [tDBInput_1 end ] stop
				 */

				/**
				 * [tAdvancedHash_row9 end ] start
				 */

				currentComponent = "tAdvancedHash_row9";

				tHash_Lookup_row9.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
				}

				ok_Hash.put("tAdvancedHash_row9", true);
				end_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row9 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row9 finally ] start
				 */

				currentComponent = "tAdvancedHash_row9";

				/**
				 * [tAdvancedHash_row9 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row11Struct implements routines.system.IPersistableComparableLookupRow<row11Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int type_operation_id;

		public int getType_operation_id() {
			return this.type_operation_id;
		}

		public String Type_d_operation;

		public String getType_d_operation() {
			return this.Type_d_operation;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Type_d_operation == null) ? 0 : this.Type_d_operation.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row11Struct other = (row11Struct) obj;

			if (this.Type_d_operation == null) {
				if (other.Type_d_operation != null)
					return false;

			} else if (!this.Type_d_operation.equals(other.Type_d_operation))

				return false;

			return true;
		}

		public void copyDataTo(row11Struct other) {

			other.type_operation_id = this.type_operation_id;
			other.Type_d_operation = this.Type_d_operation;

		}

		public void copyKeysDataTo(row11Struct other) {

			other.Type_d_operation = this.Type_d_operation;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Type_d_operation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Type_d_operation = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_d_operation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_d_operation, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.type_operation_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.type_operation_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.type_operation_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.type_operation_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("type_operation_id=" + String.valueOf(type_operation_id));
			sb.append(",Type_d_operation=" + Type_d_operation);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Type_d_operation, other.Type_d_operation);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_12Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_12_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row11Struct row11 = new row11Struct();

				/**
				 * [tAdvancedHash_row11 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row11", false);
				start_Hash.put("tAdvancedHash_row11", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row11";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row11");
				}

				int tos_count_tAdvancedHash_row11 = 0;

				// connection name:row11
				// source node:tDBInput_12 - inputs:(after_tFileInputExcel_2)
				// outputs:(row11,row11) | target node:tAdvancedHash_row11 - inputs:(row11)
				// outputs:()
				// linked node: tMap_2 -
				// inputs:(Correction,row9,row11,row8,row7,row6,row5,row4,row3) outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row11 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row11Struct> tHash_Lookup_row11 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row11Struct>getLookup(matchingModeEnum_row11);

				globalMap.put("tHash_Lookup_row11", tHash_Lookup_row11);

				/**
				 * [tAdvancedHash_row11 begin ] stop
				 */

				/**
				 * [tDBInput_12 begin ] start
				 */

				ok_Hash.put("tDBInput_12", false);
				start_Hash.put("tDBInput_12", System.currentTimeMillis());

				currentComponent = "tDBInput_12";

				int tos_count_tDBInput_12 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_12 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_12 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_12 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_12, talendToDBArray_tDBInput_12);
				int nb_line_tDBInput_12 = 0;
				java.sql.Connection conn_tDBInput_12 = null;
				String driverClass_tDBInput_12 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_12 = java.lang.Class.forName(driverClass_tDBInput_12);
				String dbUser_tDBInput_12 = "sa";

				final String decryptedPassword_tDBInput_12 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:5pKAtJffMHasa13aMMQYvjv/3HAR7rfWrXL0P7Jm/0CCsQYUIro=");

				String dbPwd_tDBInput_12 = decryptedPassword_tDBInput_12;

				String port_tDBInput_12 = "1433";
				String dbname_tDBInput_12 = "orange_DW__";
				String url_tDBInput_12 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_12)) {
					url_tDBInput_12 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_12)) {
					url_tDBInput_12 += "//" + "orange_DW__";
				}
				url_tDBInput_12 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_12 = "dbo";

				conn_tDBInput_12 = java.sql.DriverManager.getConnection(url_tDBInput_12, dbUser_tDBInput_12,
						dbPwd_tDBInput_12);

				java.sql.Statement stmt_tDBInput_12 = conn_tDBInput_12.createStatement();

				String dbquery_tDBInput_12 = "SELECT dbo.Dim_type_operation.type_operation_id,\n		dbo.Dim_type_operation.Type_d_operation\nFROM	dbo.Dim_type_operation";

				globalMap.put("tDBInput_12_QUERY", dbquery_tDBInput_12);
				java.sql.ResultSet rs_tDBInput_12 = null;

				try {
					rs_tDBInput_12 = stmt_tDBInput_12.executeQuery(dbquery_tDBInput_12);
					java.sql.ResultSetMetaData rsmd_tDBInput_12 = rs_tDBInput_12.getMetaData();
					int colQtyInRs_tDBInput_12 = rsmd_tDBInput_12.getColumnCount();

					String tmpContent_tDBInput_12 = null;

					while (rs_tDBInput_12.next()) {
						nb_line_tDBInput_12++;

						if (colQtyInRs_tDBInput_12 < 1) {
							row11.type_operation_id = 0;
						} else {

							row11.type_operation_id = rs_tDBInput_12.getInt(1);
							if (rs_tDBInput_12.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_12 < 2) {
							row11.Type_d_operation = null;
						} else {

							tmpContent_tDBInput_12 = rs_tDBInput_12.getString(2);
							if (tmpContent_tDBInput_12 != null) {
								if (talendToDBList_tDBInput_12.contains(
										rsmd_tDBInput_12.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row11.Type_d_operation = FormatterUtils.formatUnwithE(tmpContent_tDBInput_12);
								} else {
									row11.Type_d_operation = tmpContent_tDBInput_12;
								}
							} else {
								row11.Type_d_operation = null;
							}
						}

						/**
						 * [tDBInput_12 begin ] stop
						 */

						/**
						 * [tDBInput_12 main ] start
						 */

						currentComponent = "tDBInput_12";

						tos_count_tDBInput_12++;

						/**
						 * [tDBInput_12 main ] stop
						 */

						/**
						 * [tDBInput_12 process_data_begin ] start
						 */

						currentComponent = "tDBInput_12";

						/**
						 * [tDBInput_12 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row11 main ] start
						 */

						currentComponent = "tAdvancedHash_row11";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row11"

							);
						}

						row11Struct row11_HashRow = new row11Struct();

						row11_HashRow.type_operation_id = row11.type_operation_id;

						row11_HashRow.Type_d_operation = row11.Type_d_operation;

						tHash_Lookup_row11.put(row11_HashRow);

						tos_count_tAdvancedHash_row11++;

						/**
						 * [tAdvancedHash_row11 main ] stop
						 */

						/**
						 * [tAdvancedHash_row11 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row11";

						/**
						 * [tAdvancedHash_row11 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row11 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row11";

						/**
						 * [tAdvancedHash_row11 process_data_end ] stop
						 */

						/**
						 * [tDBInput_12 process_data_end ] start
						 */

						currentComponent = "tDBInput_12";

						/**
						 * [tDBInput_12 process_data_end ] stop
						 */

						/**
						 * [tDBInput_12 end ] start
						 */

						currentComponent = "tDBInput_12";

					}
				} finally {
					if (rs_tDBInput_12 != null) {
						rs_tDBInput_12.close();
					}
					if (stmt_tDBInput_12 != null) {
						stmt_tDBInput_12.close();
					}
					if (conn_tDBInput_12 != null && !conn_tDBInput_12.isClosed()) {

						conn_tDBInput_12.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_12_NB_LINE", nb_line_tDBInput_12);

				ok_Hash.put("tDBInput_12", true);
				end_Hash.put("tDBInput_12", System.currentTimeMillis());

				/**
				 * [tDBInput_12 end ] stop
				 */

				/**
				 * [tAdvancedHash_row11 end ] start
				 */

				currentComponent = "tAdvancedHash_row11";

				tHash_Lookup_row11.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row11");
				}

				ok_Hash.put("tAdvancedHash_row11", true);
				end_Hash.put("tAdvancedHash_row11", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row11 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_12 finally ] start
				 */

				currentComponent = "tDBInput_12";

				/**
				 * [tDBInput_12 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row11 finally ] start
				 */

				currentComponent = "tAdvancedHash_row11";

				/**
				 * [tAdvancedHash_row11 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_12_SUBPROCESS_STATE", 1);
	}

	public static class row8Struct implements routines.system.IPersistableComparableLookupRow<row8Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int sujet_id;

		public int getSujet_id() {
			return this.sujet_id;
		}

		public String Sujet;

		public String getSujet() {
			return this.Sujet;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Sujet == null) ? 0 : this.Sujet.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row8Struct other = (row8Struct) obj;

			if (this.Sujet == null) {
				if (other.Sujet != null)
					return false;

			} else if (!this.Sujet.equals(other.Sujet))

				return false;

			return true;
		}

		public void copyDataTo(row8Struct other) {

			other.sujet_id = this.sujet_id;
			other.Sujet = this.Sujet;

		}

		public void copyKeysDataTo(row8Struct other) {

			other.Sujet = this.Sujet;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Sujet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Sujet = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Sujet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Sujet, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.sujet_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.sujet_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.sujet_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.sujet_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("sujet_id=" + String.valueOf(sujet_id));
			sb.append(",Sujet=" + Sujet);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Sujet, other.Sujet);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_13Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_13_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row8Struct row8 = new row8Struct();

				/**
				 * [tAdvancedHash_row8 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row8", false);
				start_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row8";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row8");
				}

				int tos_count_tAdvancedHash_row8 = 0;

				// connection name:row8
				// source node:tDBInput_13 - inputs:(after_tFileInputExcel_2)
				// outputs:(row8,row8) | target node:tAdvancedHash_row8 - inputs:(row8)
				// outputs:()
				// linked node: tMap_2 -
				// inputs:(Correction,row9,row11,row8,row7,row6,row5,row4,row3) outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row8 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row8Struct>getLookup(matchingModeEnum_row8);

				globalMap.put("tHash_Lookup_row8", tHash_Lookup_row8);

				/**
				 * [tAdvancedHash_row8 begin ] stop
				 */

				/**
				 * [tDBInput_13 begin ] start
				 */

				ok_Hash.put("tDBInput_13", false);
				start_Hash.put("tDBInput_13", System.currentTimeMillis());

				currentComponent = "tDBInput_13";

				int tos_count_tDBInput_13 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_13 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_13 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_13 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_13, talendToDBArray_tDBInput_13);
				int nb_line_tDBInput_13 = 0;
				java.sql.Connection conn_tDBInput_13 = null;
				String driverClass_tDBInput_13 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_13 = java.lang.Class.forName(driverClass_tDBInput_13);
				String dbUser_tDBInput_13 = "sa";

				final String decryptedPassword_tDBInput_13 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:PxZQ0PlJsqv9JROl4HRSzib8Y2RBEdoBlBXfNalp8bTgGL5/jJk=");

				String dbPwd_tDBInput_13 = decryptedPassword_tDBInput_13;

				String port_tDBInput_13 = "1433";
				String dbname_tDBInput_13 = "orange_DW__";
				String url_tDBInput_13 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_13)) {
					url_tDBInput_13 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_13)) {
					url_tDBInput_13 += "//" + "orange_DW__";
				}
				url_tDBInput_13 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_13 = "dbo";

				conn_tDBInput_13 = java.sql.DriverManager.getConnection(url_tDBInput_13, dbUser_tDBInput_13,
						dbPwd_tDBInput_13);

				java.sql.Statement stmt_tDBInput_13 = conn_tDBInput_13.createStatement();

				String dbquery_tDBInput_13 = "SELECT dbo.Dim_sujet.sujet_id,\n		dbo.Dim_sujet.Sujet\nFROM	dbo.Dim_sujet";

				globalMap.put("tDBInput_13_QUERY", dbquery_tDBInput_13);
				java.sql.ResultSet rs_tDBInput_13 = null;

				try {
					rs_tDBInput_13 = stmt_tDBInput_13.executeQuery(dbquery_tDBInput_13);
					java.sql.ResultSetMetaData rsmd_tDBInput_13 = rs_tDBInput_13.getMetaData();
					int colQtyInRs_tDBInput_13 = rsmd_tDBInput_13.getColumnCount();

					String tmpContent_tDBInput_13 = null;

					while (rs_tDBInput_13.next()) {
						nb_line_tDBInput_13++;

						if (colQtyInRs_tDBInput_13 < 1) {
							row8.sujet_id = 0;
						} else {

							row8.sujet_id = rs_tDBInput_13.getInt(1);
							if (rs_tDBInput_13.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_13 < 2) {
							row8.Sujet = null;
						} else {

							tmpContent_tDBInput_13 = rs_tDBInput_13.getString(2);
							if (tmpContent_tDBInput_13 != null) {
								if (talendToDBList_tDBInput_13.contains(
										rsmd_tDBInput_13.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row8.Sujet = FormatterUtils.formatUnwithE(tmpContent_tDBInput_13);
								} else {
									row8.Sujet = tmpContent_tDBInput_13;
								}
							} else {
								row8.Sujet = null;
							}
						}

						/**
						 * [tDBInput_13 begin ] stop
						 */

						/**
						 * [tDBInput_13 main ] start
						 */

						currentComponent = "tDBInput_13";

						tos_count_tDBInput_13++;

						/**
						 * [tDBInput_13 main ] stop
						 */

						/**
						 * [tDBInput_13 process_data_begin ] start
						 */

						currentComponent = "tDBInput_13";

						/**
						 * [tDBInput_13 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row8 main ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row8"

							);
						}

						row8Struct row8_HashRow = new row8Struct();

						row8_HashRow.sujet_id = row8.sujet_id;

						row8_HashRow.Sujet = row8.Sujet;

						tHash_Lookup_row8.put(row8_HashRow);

						tos_count_tAdvancedHash_row8++;

						/**
						 * [tAdvancedHash_row8 main ] stop
						 */

						/**
						 * [tAdvancedHash_row8 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						/**
						 * [tAdvancedHash_row8 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row8 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						/**
						 * [tAdvancedHash_row8 process_data_end ] stop
						 */

						/**
						 * [tDBInput_13 process_data_end ] start
						 */

						currentComponent = "tDBInput_13";

						/**
						 * [tDBInput_13 process_data_end ] stop
						 */

						/**
						 * [tDBInput_13 end ] start
						 */

						currentComponent = "tDBInput_13";

					}
				} finally {
					if (rs_tDBInput_13 != null) {
						rs_tDBInput_13.close();
					}
					if (stmt_tDBInput_13 != null) {
						stmt_tDBInput_13.close();
					}
					if (conn_tDBInput_13 != null && !conn_tDBInput_13.isClosed()) {

						conn_tDBInput_13.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_13_NB_LINE", nb_line_tDBInput_13);

				ok_Hash.put("tDBInput_13", true);
				end_Hash.put("tDBInput_13", System.currentTimeMillis());

				/**
				 * [tDBInput_13 end ] stop
				 */

				/**
				 * [tAdvancedHash_row8 end ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				tHash_Lookup_row8.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row8");
				}

				ok_Hash.put("tAdvancedHash_row8", true);
				end_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row8 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_13 finally ] start
				 */

				currentComponent = "tDBInput_13";

				/**
				 * [tDBInput_13 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row8 finally ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				/**
				 * [tAdvancedHash_row8 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_13_SUBPROCESS_STATE", 1);
	}

	public static class row7Struct implements routines.system.IPersistableComparableLookupRow<row7Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int statu_final_id;

		public int getStatu_final_id() {
			return this.statu_final_id;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Staut_Final == null) ? 0 : this.Staut_Final.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row7Struct other = (row7Struct) obj;

			if (this.Staut_Final == null) {
				if (other.Staut_Final != null)
					return false;

			} else if (!this.Staut_Final.equals(other.Staut_Final))

				return false;

			return true;
		}

		public void copyDataTo(row7Struct other) {

			other.statu_final_id = this.statu_final_id;
			other.Staut_Final = this.Staut_Final;

		}

		public void copyKeysDataTo(row7Struct other) {

			other.Staut_Final = this.Staut_Final;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Staut_Final = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Staut_Final = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Staut_Final, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Staut_Final, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.statu_final_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.statu_final_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.statu_final_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.statu_final_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("statu_final_id=" + String.valueOf(statu_final_id));
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Staut_Final, other.Staut_Final);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_14Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_14_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row7Struct row7 = new row7Struct();

				/**
				 * [tAdvancedHash_row7 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row7", false);
				start_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row7";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
				}

				int tos_count_tAdvancedHash_row7 = 0;

				// connection name:row7
				// source node:tDBInput_14 - inputs:(after_tFileInputExcel_2)
				// outputs:(row7,row7) | target node:tAdvancedHash_row7 - inputs:(row7)
				// outputs:()
				// linked node: tMap_2 -
				// inputs:(Correction,row9,row11,row8,row7,row6,row5,row4,row3) outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row7 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row7Struct>getLookup(matchingModeEnum_row7);

				globalMap.put("tHash_Lookup_row7", tHash_Lookup_row7);

				/**
				 * [tAdvancedHash_row7 begin ] stop
				 */

				/**
				 * [tDBInput_14 begin ] start
				 */

				ok_Hash.put("tDBInput_14", false);
				start_Hash.put("tDBInput_14", System.currentTimeMillis());

				currentComponent = "tDBInput_14";

				int tos_count_tDBInput_14 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_14 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_14 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_14 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_14, talendToDBArray_tDBInput_14);
				int nb_line_tDBInput_14 = 0;
				java.sql.Connection conn_tDBInput_14 = null;
				String driverClass_tDBInput_14 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_14 = java.lang.Class.forName(driverClass_tDBInput_14);
				String dbUser_tDBInput_14 = "sa";

				final String decryptedPassword_tDBInput_14 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:O5dGmLrh5CJWmw+NePFbq/tHH7T+JTwp4qSo6aQ/kHqYJStMfdE=");

				String dbPwd_tDBInput_14 = decryptedPassword_tDBInput_14;

				String port_tDBInput_14 = "1433";
				String dbname_tDBInput_14 = "orange_DW__";
				String url_tDBInput_14 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_14)) {
					url_tDBInput_14 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_14)) {
					url_tDBInput_14 += "//" + "orange_DW__";
				}
				url_tDBInput_14 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_14 = "dbo";

				conn_tDBInput_14 = java.sql.DriverManager.getConnection(url_tDBInput_14, dbUser_tDBInput_14,
						dbPwd_tDBInput_14);

				java.sql.Statement stmt_tDBInput_14 = conn_tDBInput_14.createStatement();

				String dbquery_tDBInput_14 = "SELECT dbo.Dim_statu_final.statu_final_id,\n		dbo.Dim_statu_final.Staut_Final\nFROM	dbo.Dim_statu_final";

				globalMap.put("tDBInput_14_QUERY", dbquery_tDBInput_14);
				java.sql.ResultSet rs_tDBInput_14 = null;

				try {
					rs_tDBInput_14 = stmt_tDBInput_14.executeQuery(dbquery_tDBInput_14);
					java.sql.ResultSetMetaData rsmd_tDBInput_14 = rs_tDBInput_14.getMetaData();
					int colQtyInRs_tDBInput_14 = rsmd_tDBInput_14.getColumnCount();

					String tmpContent_tDBInput_14 = null;

					while (rs_tDBInput_14.next()) {
						nb_line_tDBInput_14++;

						if (colQtyInRs_tDBInput_14 < 1) {
							row7.statu_final_id = 0;
						} else {

							row7.statu_final_id = rs_tDBInput_14.getInt(1);
							if (rs_tDBInput_14.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_14 < 2) {
							row7.Staut_Final = null;
						} else {

							tmpContent_tDBInput_14 = rs_tDBInput_14.getString(2);
							if (tmpContent_tDBInput_14 != null) {
								if (talendToDBList_tDBInput_14.contains(
										rsmd_tDBInput_14.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row7.Staut_Final = FormatterUtils.formatUnwithE(tmpContent_tDBInput_14);
								} else {
									row7.Staut_Final = tmpContent_tDBInput_14;
								}
							} else {
								row7.Staut_Final = null;
							}
						}

						/**
						 * [tDBInput_14 begin ] stop
						 */

						/**
						 * [tDBInput_14 main ] start
						 */

						currentComponent = "tDBInput_14";

						tos_count_tDBInput_14++;

						/**
						 * [tDBInput_14 main ] stop
						 */

						/**
						 * [tDBInput_14 process_data_begin ] start
						 */

						currentComponent = "tDBInput_14";

						/**
						 * [tDBInput_14 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row7 main ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row7"

							);
						}

						row7Struct row7_HashRow = new row7Struct();

						row7_HashRow.statu_final_id = row7.statu_final_id;

						row7_HashRow.Staut_Final = row7.Staut_Final;

						tHash_Lookup_row7.put(row7_HashRow);

						tos_count_tAdvancedHash_row7++;

						/**
						 * [tAdvancedHash_row7 main ] stop
						 */

						/**
						 * [tAdvancedHash_row7 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						/**
						 * [tAdvancedHash_row7 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row7 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						/**
						 * [tAdvancedHash_row7 process_data_end ] stop
						 */

						/**
						 * [tDBInput_14 process_data_end ] start
						 */

						currentComponent = "tDBInput_14";

						/**
						 * [tDBInput_14 process_data_end ] stop
						 */

						/**
						 * [tDBInput_14 end ] start
						 */

						currentComponent = "tDBInput_14";

					}
				} finally {
					if (rs_tDBInput_14 != null) {
						rs_tDBInput_14.close();
					}
					if (stmt_tDBInput_14 != null) {
						stmt_tDBInput_14.close();
					}
					if (conn_tDBInput_14 != null && !conn_tDBInput_14.isClosed()) {

						conn_tDBInput_14.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_14_NB_LINE", nb_line_tDBInput_14);

				ok_Hash.put("tDBInput_14", true);
				end_Hash.put("tDBInput_14", System.currentTimeMillis());

				/**
				 * [tDBInput_14 end ] stop
				 */

				/**
				 * [tAdvancedHash_row7 end ] start
				 */

				currentComponent = "tAdvancedHash_row7";

				tHash_Lookup_row7.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
				}

				ok_Hash.put("tAdvancedHash_row7", true);
				end_Hash.put("tAdvancedHash_row7", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row7 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_14 finally ] start
				 */

				currentComponent = "tDBInput_14";

				/**
				 * [tDBInput_14 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row7 finally ] start
				 */

				currentComponent = "tAdvancedHash_row7";

				/**
				 * [tAdvancedHash_row7 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_14_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableComparableLookupRow<row6Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int equipe_id;

		public int getEquipe_id() {
			return this.equipe_id;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Equipe == null) ? 0 : this.Equipe.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row6Struct other = (row6Struct) obj;

			if (this.Equipe == null) {
				if (other.Equipe != null)
					return false;

			} else if (!this.Equipe.equals(other.Equipe))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other.equipe_id = this.equipe_id;
			other.Equipe = this.Equipe;

		}

		public void copyKeysDataTo(row6Struct other) {

			other.Equipe = this.Equipe;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Equipe = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Equipe = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Equipe, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Equipe, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.equipe_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.equipe_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.equipe_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.equipe_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("equipe_id=" + String.valueOf(equipe_id));
			sb.append(",Equipe=" + Equipe);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Equipe, other.Equipe);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_15Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_15_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row6Struct row6 = new row6Struct();

				/**
				 * [tAdvancedHash_row6 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row6", false);
				start_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tAdvancedHash_row6 = 0;

				// connection name:row6
				// source node:tDBInput_15 - inputs:(after_tFileInputExcel_2)
				// outputs:(row6,row6) | target node:tAdvancedHash_row6 - inputs:(row6)
				// outputs:()
				// linked node: tMap_2 -
				// inputs:(Correction,row9,row11,row8,row7,row6,row5,row4,row3) outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row6 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row6Struct>getLookup(matchingModeEnum_row6);

				globalMap.put("tHash_Lookup_row6", tHash_Lookup_row6);

				/**
				 * [tAdvancedHash_row6 begin ] stop
				 */

				/**
				 * [tDBInput_15 begin ] start
				 */

				ok_Hash.put("tDBInput_15", false);
				start_Hash.put("tDBInput_15", System.currentTimeMillis());

				currentComponent = "tDBInput_15";

				int tos_count_tDBInput_15 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_15 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_15 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_15 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_15, talendToDBArray_tDBInput_15);
				int nb_line_tDBInput_15 = 0;
				java.sql.Connection conn_tDBInput_15 = null;
				String driverClass_tDBInput_15 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_15 = java.lang.Class.forName(driverClass_tDBInput_15);
				String dbUser_tDBInput_15 = "sa";

				final String decryptedPassword_tDBInput_15 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:RT5Ow6KMjYyGjMm4ZDlvp0ZWRzPn/Gt+ECvJVO58/Qq/W+kYtIo=");

				String dbPwd_tDBInput_15 = decryptedPassword_tDBInput_15;

				String port_tDBInput_15 = "1433";
				String dbname_tDBInput_15 = "orange_DW__";
				String url_tDBInput_15 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_15)) {
					url_tDBInput_15 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_15)) {
					url_tDBInput_15 += "//" + "orange_DW__";
				}
				url_tDBInput_15 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_15 = "dbo";

				conn_tDBInput_15 = java.sql.DriverManager.getConnection(url_tDBInput_15, dbUser_tDBInput_15,
						dbPwd_tDBInput_15);

				java.sql.Statement stmt_tDBInput_15 = conn_tDBInput_15.createStatement();

				String dbquery_tDBInput_15 = "SELECT dbo.Dim_equipe.equipe_id,\n		dbo.Dim_equipe.Equipe\nFROM	dbo.Dim_equipe";

				globalMap.put("tDBInput_15_QUERY", dbquery_tDBInput_15);
				java.sql.ResultSet rs_tDBInput_15 = null;

				try {
					rs_tDBInput_15 = stmt_tDBInput_15.executeQuery(dbquery_tDBInput_15);
					java.sql.ResultSetMetaData rsmd_tDBInput_15 = rs_tDBInput_15.getMetaData();
					int colQtyInRs_tDBInput_15 = rsmd_tDBInput_15.getColumnCount();

					String tmpContent_tDBInput_15 = null;

					while (rs_tDBInput_15.next()) {
						nb_line_tDBInput_15++;

						if (colQtyInRs_tDBInput_15 < 1) {
							row6.equipe_id = 0;
						} else {

							row6.equipe_id = rs_tDBInput_15.getInt(1);
							if (rs_tDBInput_15.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_15 < 2) {
							row6.Equipe = null;
						} else {

							tmpContent_tDBInput_15 = rs_tDBInput_15.getString(2);
							if (tmpContent_tDBInput_15 != null) {
								if (talendToDBList_tDBInput_15.contains(
										rsmd_tDBInput_15.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row6.Equipe = FormatterUtils.formatUnwithE(tmpContent_tDBInput_15);
								} else {
									row6.Equipe = tmpContent_tDBInput_15;
								}
							} else {
								row6.Equipe = null;
							}
						}

						/**
						 * [tDBInput_15 begin ] stop
						 */

						/**
						 * [tDBInput_15 main ] start
						 */

						currentComponent = "tDBInput_15";

						tos_count_tDBInput_15++;

						/**
						 * [tDBInput_15 main ] stop
						 */

						/**
						 * [tDBInput_15 process_data_begin ] start
						 */

						currentComponent = "tDBInput_15";

						/**
						 * [tDBInput_15 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row6 main ] start
						 */

						currentComponent = "tAdvancedHash_row6";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row6"

							);
						}

						row6Struct row6_HashRow = new row6Struct();

						row6_HashRow.equipe_id = row6.equipe_id;

						row6_HashRow.Equipe = row6.Equipe;

						tHash_Lookup_row6.put(row6_HashRow);

						tos_count_tAdvancedHash_row6++;

						/**
						 * [tAdvancedHash_row6 main ] stop
						 */

						/**
						 * [tAdvancedHash_row6 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row6";

						/**
						 * [tAdvancedHash_row6 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row6 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row6";

						/**
						 * [tAdvancedHash_row6 process_data_end ] stop
						 */

						/**
						 * [tDBInput_15 process_data_end ] start
						 */

						currentComponent = "tDBInput_15";

						/**
						 * [tDBInput_15 process_data_end ] stop
						 */

						/**
						 * [tDBInput_15 end ] start
						 */

						currentComponent = "tDBInput_15";

					}
				} finally {
					if (rs_tDBInput_15 != null) {
						rs_tDBInput_15.close();
					}
					if (stmt_tDBInput_15 != null) {
						stmt_tDBInput_15.close();
					}
					if (conn_tDBInput_15 != null && !conn_tDBInput_15.isClosed()) {

						conn_tDBInput_15.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_15_NB_LINE", nb_line_tDBInput_15);

				ok_Hash.put("tDBInput_15", true);
				end_Hash.put("tDBInput_15", System.currentTimeMillis());

				/**
				 * [tDBInput_15 end ] stop
				 */

				/**
				 * [tAdvancedHash_row6 end ] start
				 */

				currentComponent = "tAdvancedHash_row6";

				tHash_Lookup_row6.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tAdvancedHash_row6", true);
				end_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row6 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_15 finally ] start
				 */

				currentComponent = "tDBInput_15";

				/**
				 * [tDBInput_15 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row6 finally ] start
				 */

				currentComponent = "tAdvancedHash_row6";

				/**
				 * [tAdvancedHash_row6 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_15_SUBPROCESS_STATE", 1);
	}

	public static class row5Struct implements routines.system.IPersistableComparableLookupRow<row5Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Client_Prospect_id;

		public int getClient_Prospect_id() {
			return this.Client_Prospect_id;
		}

		public String Client_Prospect;

		public String getClient_Prospect() {
			return this.Client_Prospect;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Client_Prospect == null) ? 0 : this.Client_Prospect.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row5Struct other = (row5Struct) obj;

			if (this.Client_Prospect == null) {
				if (other.Client_Prospect != null)
					return false;

			} else if (!this.Client_Prospect.equals(other.Client_Prospect))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.Client_Prospect_id = this.Client_Prospect_id;
			other.Client_Prospect = this.Client_Prospect;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.Client_Prospect = this.Client_Prospect;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Client_Prospect, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Client_Prospect, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.Client_Prospect_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Client_Prospect_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.Client_Prospect_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.Client_Prospect_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Client_Prospect_id=" + String.valueOf(Client_Prospect_id));
			sb.append(",Client_Prospect=" + Client_Prospect);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Client_Prospect, other.Client_Prospect);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_17Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_17_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row5Struct row5 = new row5Struct();

				/**
				 * [tAdvancedHash_row5 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row5", false);
				start_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tAdvancedHash_row5 = 0;

				// connection name:row5
				// source node:tDBInput_17 - inputs:(after_tFileInputExcel_2)
				// outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5)
				// outputs:()
				// linked node: tMap_2 -
				// inputs:(Correction,row9,row11,row8,row7,row6,row5,row4,row3) outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row5Struct>getLookup(matchingModeEnum_row5);

				globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);

				/**
				 * [tAdvancedHash_row5 begin ] stop
				 */

				/**
				 * [tDBInput_17 begin ] start
				 */

				ok_Hash.put("tDBInput_17", false);
				start_Hash.put("tDBInput_17", System.currentTimeMillis());

				currentComponent = "tDBInput_17";

				int tos_count_tDBInput_17 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_17 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_17 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_17 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_17, talendToDBArray_tDBInput_17);
				int nb_line_tDBInput_17 = 0;
				java.sql.Connection conn_tDBInput_17 = null;
				String driverClass_tDBInput_17 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_17 = java.lang.Class.forName(driverClass_tDBInput_17);
				String dbUser_tDBInput_17 = "sa";

				final String decryptedPassword_tDBInput_17 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:C7wFxajRsHbfezBXllJIH9xV3DV0ioMlMLqvECshKPLaQG2plbo=");

				String dbPwd_tDBInput_17 = decryptedPassword_tDBInput_17;

				String port_tDBInput_17 = "1433";
				String dbname_tDBInput_17 = "orange_DW__";
				String url_tDBInput_17 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_17)) {
					url_tDBInput_17 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_17)) {
					url_tDBInput_17 += "//" + "orange_DW__";
				}
				url_tDBInput_17 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_17 = "dbo";

				conn_tDBInput_17 = java.sql.DriverManager.getConnection(url_tDBInput_17, dbUser_tDBInput_17,
						dbPwd_tDBInput_17);

				java.sql.Statement stmt_tDBInput_17 = conn_tDBInput_17.createStatement();

				String dbquery_tDBInput_17 = "SELECT dbo.Dim_Raison_sociale.Client_Prospect_id,\n		dbo.Dim_Raison_sociale.Client_Prospect\nFROM	dbo.Dim_Raison_sociale";

				globalMap.put("tDBInput_17_QUERY", dbquery_tDBInput_17);
				java.sql.ResultSet rs_tDBInput_17 = null;

				try {
					rs_tDBInput_17 = stmt_tDBInput_17.executeQuery(dbquery_tDBInput_17);
					java.sql.ResultSetMetaData rsmd_tDBInput_17 = rs_tDBInput_17.getMetaData();
					int colQtyInRs_tDBInput_17 = rsmd_tDBInput_17.getColumnCount();

					String tmpContent_tDBInput_17 = null;

					while (rs_tDBInput_17.next()) {
						nb_line_tDBInput_17++;

						if (colQtyInRs_tDBInput_17 < 1) {
							row5.Client_Prospect_id = 0;
						} else {

							row5.Client_Prospect_id = rs_tDBInput_17.getInt(1);
							if (rs_tDBInput_17.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_17 < 2) {
							row5.Client_Prospect = null;
						} else {

							tmpContent_tDBInput_17 = rs_tDBInput_17.getString(2);
							if (tmpContent_tDBInput_17 != null) {
								if (talendToDBList_tDBInput_17.contains(
										rsmd_tDBInput_17.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row5.Client_Prospect = FormatterUtils.formatUnwithE(tmpContent_tDBInput_17);
								} else {
									row5.Client_Prospect = tmpContent_tDBInput_17;
								}
							} else {
								row5.Client_Prospect = null;
							}
						}

						/**
						 * [tDBInput_17 begin ] stop
						 */

						/**
						 * [tDBInput_17 main ] start
						 */

						currentComponent = "tDBInput_17";

						tos_count_tDBInput_17++;

						/**
						 * [tDBInput_17 main ] stop
						 */

						/**
						 * [tDBInput_17 process_data_begin ] start
						 */

						currentComponent = "tDBInput_17";

						/**
						 * [tDBInput_17 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row5 main ] start
						 */

						currentComponent = "tAdvancedHash_row5";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row5"

							);
						}

						row5Struct row5_HashRow = new row5Struct();

						row5_HashRow.Client_Prospect_id = row5.Client_Prospect_id;

						row5_HashRow.Client_Prospect = row5.Client_Prospect;

						tHash_Lookup_row5.put(row5_HashRow);

						tos_count_tAdvancedHash_row5++;

						/**
						 * [tAdvancedHash_row5 main ] stop
						 */

						/**
						 * [tAdvancedHash_row5 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row5";

						/**
						 * [tAdvancedHash_row5 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row5 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row5";

						/**
						 * [tAdvancedHash_row5 process_data_end ] stop
						 */

						/**
						 * [tDBInput_17 process_data_end ] start
						 */

						currentComponent = "tDBInput_17";

						/**
						 * [tDBInput_17 process_data_end ] stop
						 */

						/**
						 * [tDBInput_17 end ] start
						 */

						currentComponent = "tDBInput_17";

					}
				} finally {
					if (rs_tDBInput_17 != null) {
						rs_tDBInput_17.close();
					}
					if (stmt_tDBInput_17 != null) {
						stmt_tDBInput_17.close();
					}
					if (conn_tDBInput_17 != null && !conn_tDBInput_17.isClosed()) {

						conn_tDBInput_17.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_17_NB_LINE", nb_line_tDBInput_17);

				ok_Hash.put("tDBInput_17", true);
				end_Hash.put("tDBInput_17", System.currentTimeMillis());

				/**
				 * [tDBInput_17 end ] stop
				 */

				/**
				 * [tAdvancedHash_row5 end ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				tHash_Lookup_row5.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tAdvancedHash_row5", true);
				end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row5 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_17 finally ] start
				 */

				currentComponent = "tDBInput_17";

				/**
				 * [tDBInput_17 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row5 finally ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				/**
				 * [tAdvancedHash_row5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_17_SUBPROCESS_STATE", 1);
	}

	public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int type_acces_id;

		public int getType_acces_id() {
			return this.type_acces_id;
		}

		public String Type_d_acces;

		public String getType_d_acces() {
			return this.Type_d_acces;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Type_d_acces == null) ? 0 : this.Type_d_acces.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row4Struct other = (row4Struct) obj;

			if (this.Type_d_acces == null) {
				if (other.Type_d_acces != null)
					return false;

			} else if (!this.Type_d_acces.equals(other.Type_d_acces))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.type_acces_id = this.type_acces_id;
			other.Type_d_acces = this.Type_d_acces;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.Type_d_acces = this.Type_d_acces;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Type_d_acces = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Type_d_acces = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_d_acces, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_d_acces, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.type_acces_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.type_acces_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.type_acces_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.type_acces_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("type_acces_id=" + String.valueOf(type_acces_id));
			sb.append(",Type_d_acces=" + Type_d_acces);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Type_d_acces, other.Type_d_acces);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_18Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_18_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();

				/**
				 * [tAdvancedHash_row4 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row4", false);
				start_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tAdvancedHash_row4 = 0;

				// connection name:row4
				// source node:tDBInput_18 - inputs:(after_tFileInputExcel_2)
				// outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4)
				// outputs:()
				// linked node: tMap_2 -
				// inputs:(Correction,row9,row11,row8,row7,row6,row5,row4,row3) outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row4Struct>getLookup(matchingModeEnum_row4);

				globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);

				/**
				 * [tAdvancedHash_row4 begin ] stop
				 */

				/**
				 * [tDBInput_18 begin ] start
				 */

				ok_Hash.put("tDBInput_18", false);
				start_Hash.put("tDBInput_18", System.currentTimeMillis());

				currentComponent = "tDBInput_18";

				int tos_count_tDBInput_18 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_18 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_18 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_18 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_18, talendToDBArray_tDBInput_18);
				int nb_line_tDBInput_18 = 0;
				java.sql.Connection conn_tDBInput_18 = null;
				String driverClass_tDBInput_18 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_18 = java.lang.Class.forName(driverClass_tDBInput_18);
				String dbUser_tDBInput_18 = "sa";

				final String decryptedPassword_tDBInput_18 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:MdEez8ZRGkTCPjlaF5Gz89HmX4mkJU/AsMT+64vMFsYcFHnxO/8=");

				String dbPwd_tDBInput_18 = decryptedPassword_tDBInput_18;

				String port_tDBInput_18 = "1433";
				String dbname_tDBInput_18 = "orange_DW__";
				String url_tDBInput_18 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_18)) {
					url_tDBInput_18 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_18)) {
					url_tDBInput_18 += "//" + "orange_DW__";
				}
				url_tDBInput_18 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_18 = "dbo";

				conn_tDBInput_18 = java.sql.DriverManager.getConnection(url_tDBInput_18, dbUser_tDBInput_18,
						dbPwd_tDBInput_18);

				java.sql.Statement stmt_tDBInput_18 = conn_tDBInput_18.createStatement();

				String dbquery_tDBInput_18 = "SELECT dbo.Dim_type_acces.\"type_accés_id\",\n		dbo.Dim_type_acces.Type_d_acces\nFROM	dbo.Dim_type_acces";

				globalMap.put("tDBInput_18_QUERY", dbquery_tDBInput_18);
				java.sql.ResultSet rs_tDBInput_18 = null;

				try {
					rs_tDBInput_18 = stmt_tDBInput_18.executeQuery(dbquery_tDBInput_18);
					java.sql.ResultSetMetaData rsmd_tDBInput_18 = rs_tDBInput_18.getMetaData();
					int colQtyInRs_tDBInput_18 = rsmd_tDBInput_18.getColumnCount();

					String tmpContent_tDBInput_18 = null;

					while (rs_tDBInput_18.next()) {
						nb_line_tDBInput_18++;

						if (colQtyInRs_tDBInput_18 < 1) {
							row4.type_acces_id = 0;
						} else {

							row4.type_acces_id = rs_tDBInput_18.getInt(1);
							if (rs_tDBInput_18.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_18 < 2) {
							row4.Type_d_acces = null;
						} else {

							tmpContent_tDBInput_18 = rs_tDBInput_18.getString(2);
							if (tmpContent_tDBInput_18 != null) {
								if (talendToDBList_tDBInput_18.contains(
										rsmd_tDBInput_18.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row4.Type_d_acces = FormatterUtils.formatUnwithE(tmpContent_tDBInput_18);
								} else {
									row4.Type_d_acces = tmpContent_tDBInput_18;
								}
							} else {
								row4.Type_d_acces = null;
							}
						}

						/**
						 * [tDBInput_18 begin ] stop
						 */

						/**
						 * [tDBInput_18 main ] start
						 */

						currentComponent = "tDBInput_18";

						tos_count_tDBInput_18++;

						/**
						 * [tDBInput_18 main ] stop
						 */

						/**
						 * [tDBInput_18 process_data_begin ] start
						 */

						currentComponent = "tDBInput_18";

						/**
						 * [tDBInput_18 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row4 main ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row4"

							);
						}

						row4Struct row4_HashRow = new row4Struct();

						row4_HashRow.type_acces_id = row4.type_acces_id;

						row4_HashRow.Type_d_acces = row4.Type_d_acces;

						tHash_Lookup_row4.put(row4_HashRow);

						tos_count_tAdvancedHash_row4++;

						/**
						 * [tAdvancedHash_row4 main ] stop
						 */

						/**
						 * [tAdvancedHash_row4 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						/**
						 * [tAdvancedHash_row4 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row4 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						/**
						 * [tAdvancedHash_row4 process_data_end ] stop
						 */

						/**
						 * [tDBInput_18 process_data_end ] start
						 */

						currentComponent = "tDBInput_18";

						/**
						 * [tDBInput_18 process_data_end ] stop
						 */

						/**
						 * [tDBInput_18 end ] start
						 */

						currentComponent = "tDBInput_18";

					}
				} finally {
					if (rs_tDBInput_18 != null) {
						rs_tDBInput_18.close();
					}
					if (stmt_tDBInput_18 != null) {
						stmt_tDBInput_18.close();
					}
					if (conn_tDBInput_18 != null && !conn_tDBInput_18.isClosed()) {

						conn_tDBInput_18.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_18_NB_LINE", nb_line_tDBInput_18);

				ok_Hash.put("tDBInput_18", true);
				end_Hash.put("tDBInput_18", System.currentTimeMillis());

				/**
				 * [tDBInput_18 end ] stop
				 */

				/**
				 * [tAdvancedHash_row4 end ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				tHash_Lookup_row4.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tAdvancedHash_row4", true);
				end_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row4 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_18 finally ] start
				 */

				currentComponent = "tDBInput_18";

				/**
				 * [tDBInput_18 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row4 finally ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				/**
				 * [tAdvancedHash_row4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_18_SUBPROCESS_STATE", 1);
	}

	public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_data_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_data_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int phase_de_l_opportunite_id;

		public int getPhase_de_l_opportunite_id() {
			return this.phase_de_l_opportunite_id;
		}

		public String Phase_de_l_opportunite;

		public String getPhase_de_l_opportunite() {
			return this.Phase_de_l_opportunite;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result
						+ ((this.Phase_de_l_opportunite == null) ? 0 : this.Phase_de_l_opportunite.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row3Struct other = (row3Struct) obj;

			if (this.Phase_de_l_opportunite == null) {
				if (other.Phase_de_l_opportunite != null)
					return false;

			} else if (!this.Phase_de_l_opportunite.equals(other.Phase_de_l_opportunite))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.phase_de_l_opportunite_id = this.phase_de_l_opportunite_id;
			other.Phase_de_l_opportunite = this.Phase_de_l_opportunite;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.Phase_de_l_opportunite = this.Phase_de_l_opportunite;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_data_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_data_orange.length == 0) {
						commonByteArray_ORANGE_Base_data_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_data_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_data_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_data_orange, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Phase_de_l_opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_data_orange) {

				try {

					int length = 0;

					this.Phase_de_l_opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Phase_de_l_opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Phase_de_l_opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.phase_de_l_opportunite_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.phase_de_l_opportunite_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.phase_de_l_opportunite_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.phase_de_l_opportunite_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("phase_de_l_opportunite_id=" + String.valueOf(phase_de_l_opportunite_id));
			sb.append(",Phase_de_l_opportunite=" + Phase_de_l_opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Phase_de_l_opportunite, other.Phase_de_l_opportunite);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_19Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_19_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row3Struct row3 = new row3Struct();

				/**
				 * [tAdvancedHash_row3 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row3", false);
				start_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tAdvancedHash_row3 = 0;

				// connection name:row3
				// source node:tDBInput_19 - inputs:(after_tFileInputExcel_2)
				// outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3)
				// outputs:()
				// linked node: tMap_2 -
				// inputs:(Correction,row9,row11,row8,row7,row6,row5,row4,row3) outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row3Struct>getLookup(matchingModeEnum_row3);

				globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);

				/**
				 * [tAdvancedHash_row3 begin ] stop
				 */

				/**
				 * [tDBInput_19 begin ] start
				 */

				ok_Hash.put("tDBInput_19", false);
				start_Hash.put("tDBInput_19", System.currentTimeMillis());

				currentComponent = "tDBInput_19";

				int tos_count_tDBInput_19 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_19 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_19 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_19 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_19, talendToDBArray_tDBInput_19);
				int nb_line_tDBInput_19 = 0;
				java.sql.Connection conn_tDBInput_19 = null;
				String driverClass_tDBInput_19 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_19 = java.lang.Class.forName(driverClass_tDBInput_19);
				String dbUser_tDBInput_19 = "sa";

				final String decryptedPassword_tDBInput_19 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:KxIv/6OBcYGOqHR+XuTgltTdZ/63XWyfvVajEixj3jkLPXaLRYo=");

				String dbPwd_tDBInput_19 = decryptedPassword_tDBInput_19;

				String port_tDBInput_19 = "1433";
				String dbname_tDBInput_19 = "orange_DW__";
				String url_tDBInput_19 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_19)) {
					url_tDBInput_19 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_19)) {
					url_tDBInput_19 += "//" + "orange_DW__";
				}
				url_tDBInput_19 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_19 = "dbo";

				conn_tDBInput_19 = java.sql.DriverManager.getConnection(url_tDBInput_19, dbUser_tDBInput_19,
						dbPwd_tDBInput_19);

				java.sql.Statement stmt_tDBInput_19 = conn_tDBInput_19.createStatement();

				String dbquery_tDBInput_19 = "SELECT dbo.Dim_phase_de_l_opportunite.\"phase_de_l_opportunité_id\",\n		dbo.Dim_phase_de_l_opportunite.Phase_de_l_opport"
						+ "unite\nFROM	dbo.Dim_phase_de_l_opportunite";

				globalMap.put("tDBInput_19_QUERY", dbquery_tDBInput_19);
				java.sql.ResultSet rs_tDBInput_19 = null;

				try {
					rs_tDBInput_19 = stmt_tDBInput_19.executeQuery(dbquery_tDBInput_19);
					java.sql.ResultSetMetaData rsmd_tDBInput_19 = rs_tDBInput_19.getMetaData();
					int colQtyInRs_tDBInput_19 = rsmd_tDBInput_19.getColumnCount();

					String tmpContent_tDBInput_19 = null;

					while (rs_tDBInput_19.next()) {
						nb_line_tDBInput_19++;

						if (colQtyInRs_tDBInput_19 < 1) {
							row3.phase_de_l_opportunite_id = 0;
						} else {

							row3.phase_de_l_opportunite_id = rs_tDBInput_19.getInt(1);
							if (rs_tDBInput_19.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_19 < 2) {
							row3.Phase_de_l_opportunite = null;
						} else {

							tmpContent_tDBInput_19 = rs_tDBInput_19.getString(2);
							if (tmpContent_tDBInput_19 != null) {
								if (talendToDBList_tDBInput_19.contains(
										rsmd_tDBInput_19.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row3.Phase_de_l_opportunite = FormatterUtils.formatUnwithE(tmpContent_tDBInput_19);
								} else {
									row3.Phase_de_l_opportunite = tmpContent_tDBInput_19;
								}
							} else {
								row3.Phase_de_l_opportunite = null;
							}
						}

						/**
						 * [tDBInput_19 begin ] stop
						 */

						/**
						 * [tDBInput_19 main ] start
						 */

						currentComponent = "tDBInput_19";

						tos_count_tDBInput_19++;

						/**
						 * [tDBInput_19 main ] stop
						 */

						/**
						 * [tDBInput_19 process_data_begin ] start
						 */

						currentComponent = "tDBInput_19";

						/**
						 * [tDBInput_19 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row3 main ] start
						 */

						currentComponent = "tAdvancedHash_row3";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row3"

							);
						}

						row3Struct row3_HashRow = new row3Struct();

						row3_HashRow.phase_de_l_opportunite_id = row3.phase_de_l_opportunite_id;

						row3_HashRow.Phase_de_l_opportunite = row3.Phase_de_l_opportunite;

						tHash_Lookup_row3.put(row3_HashRow);

						tos_count_tAdvancedHash_row3++;

						/**
						 * [tAdvancedHash_row3 main ] stop
						 */

						/**
						 * [tAdvancedHash_row3 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row3";

						/**
						 * [tAdvancedHash_row3 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row3 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row3";

						/**
						 * [tAdvancedHash_row3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_19 process_data_end ] start
						 */

						currentComponent = "tDBInput_19";

						/**
						 * [tDBInput_19 process_data_end ] stop
						 */

						/**
						 * [tDBInput_19 end ] start
						 */

						currentComponent = "tDBInput_19";

					}
				} finally {
					if (rs_tDBInput_19 != null) {
						rs_tDBInput_19.close();
					}
					if (stmt_tDBInput_19 != null) {
						stmt_tDBInput_19.close();
					}
					if (conn_tDBInput_19 != null && !conn_tDBInput_19.isClosed()) {

						conn_tDBInput_19.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_19_NB_LINE", nb_line_tDBInput_19);

				ok_Hash.put("tDBInput_19", true);
				end_Hash.put("tDBInput_19", System.currentTimeMillis());

				/**
				 * [tDBInput_19 end ] stop
				 */

				/**
				 * [tAdvancedHash_row3 end ] start
				 */

				currentComponent = "tAdvancedHash_row3";

				tHash_Lookup_row3.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tAdvancedHash_row3", true);
				end_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row3 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_19 finally ] start
				 */

				currentComponent = "tDBInput_19";

				/**
				 * [tDBInput_19 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row3 finally ] start
				 */

				currentComponent = "tAdvancedHash_row3";

				/**
				 * [tAdvancedHash_row3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_19_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Base_data_orange Base_data_orangeClass = new Base_data_orange();

		int exitCode = Base_data_orangeClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Base_data_orange.class.getClassLoader()
					.getResourceAsStream("orange/base_data_orange_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Base_data_orange.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("test_RowSeparator", "id_String");
					if (context.getStringValue("test_RowSeparator") == null) {
						context.test_RowSeparator = null;
					} else {
						context.test_RowSeparator = (String) context.getProperty("test_RowSeparator");
					}
					context.setContextType("test_File", "id_File");
					if (context.getStringValue("test_File") == null) {
						context.test_File = null;
					} else {
						context.test_File = (String) context.getProperty("test_File");
					}
					context.setContextType("test_Encoding", "id_String");
					if (context.getStringValue("test_Encoding") == null) {
						context.test_Encoding = null;
					} else {
						context.test_Encoding = (String) context.getProperty("test_Encoding");
					}
					context.setContextType("test_FieldSeparator", "id_String");
					if (context.getStringValue("test_FieldSeparator") == null) {
						context.test_FieldSeparator = null;
					} else {
						context.test_FieldSeparator = (String) context.getProperty("test_FieldSeparator");
					}
					context.setContextType("test_Header", "id_Integer");
					if (context.getStringValue("test_Header") == null) {
						context.test_Header = null;
					} else {
						try {
							context.test_Header = routines.system.ParserUtils
									.parseTo_Integer(context.getProperty("test_Header"));
						} catch (NumberFormatException e) {
							System.err.println(String.format("Null value will be used for context parameter %s: %s",
									"test_Header", e.getMessage()));
							context.test_Header = null;
						}
					}
					context.setContextType("Extract4_File", "id_File");
					if (context.getStringValue("Extract4_File") == null) {
						context.Extract4_File = null;
					} else {
						context.Extract4_File = (String) context.getProperty("Extract4_File");
					}
					context.setContextType("Extract4_Encoding", "id_String");
					if (context.getStringValue("Extract4_Encoding") == null) {
						context.Extract4_Encoding = null;
					} else {
						context.Extract4_Encoding = (String) context.getProperty("Extract4_Encoding");
					}
					context.setContextType("Extract4_Header", "id_Integer");
					if (context.getStringValue("Extract4_Header") == null) {
						context.Extract4_Header = null;
					} else {
						try {
							context.Extract4_Header = routines.system.ParserUtils
									.parseTo_Integer(context.getProperty("Extract4_Header"));
						} catch (NumberFormatException e) {
							System.err.println(String.format("Null value will be used for context parameter %s: %s",
									"Extract4_Header", e.getMessage()));
							context.Extract4_Header = null;
						}
					}
					context.setContextType("Extract4_DecimalSeparator", "id_String");
					if (context.getStringValue("Extract4_DecimalSeparator") == null) {
						context.Extract4_DecimalSeparator = null;
					} else {
						context.Extract4_DecimalSeparator = (String) context.getProperty("Extract4_DecimalSeparator");
					}
					context.setContextType("Extract4_LastColumn", "id_Integer");
					if (context.getStringValue("Extract4_LastColumn") == null) {
						context.Extract4_LastColumn = null;
					} else {
						try {
							context.Extract4_LastColumn = routines.system.ParserUtils
									.parseTo_Integer(context.getProperty("Extract4_LastColumn"));
						} catch (NumberFormatException e) {
							System.err.println(String.format("Null value will be used for context parameter %s: %s",
									"Extract4_LastColumn", e.getMessage()));
							context.Extract4_LastColumn = null;
						}
					}
					context.setContextType("Extract4_ThousandSeparator", "id_String");
					if (context.getStringValue("Extract4_ThousandSeparator") == null) {
						context.Extract4_ThousandSeparator = null;
					} else {
						context.Extract4_ThousandSeparator = (String) context.getProperty("Extract4_ThousandSeparator");
					}
					context.setContextType("Extract4_FirstColumn", "id_Integer");
					if (context.getStringValue("Extract4_FirstColumn") == null) {
						context.Extract4_FirstColumn = null;
					} else {
						try {
							context.Extract4_FirstColumn = routines.system.ParserUtils
									.parseTo_Integer(context.getProperty("Extract4_FirstColumn"));
						} catch (NumberFormatException e) {
							System.err.println(String.format("Null value will be used for context parameter %s: %s",
									"Extract4_FirstColumn", e.getMessage()));
							context.Extract4_FirstColumn = null;
						}
					}
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("test_RowSeparator")) {
				context.test_RowSeparator = (String) parentContextMap.get("test_RowSeparator");
			}
			if (parentContextMap.containsKey("test_File")) {
				context.test_File = (String) parentContextMap.get("test_File");
			}
			if (parentContextMap.containsKey("test_Encoding")) {
				context.test_Encoding = (String) parentContextMap.get("test_Encoding");
			}
			if (parentContextMap.containsKey("test_FieldSeparator")) {
				context.test_FieldSeparator = (String) parentContextMap.get("test_FieldSeparator");
			}
			if (parentContextMap.containsKey("test_Header")) {
				context.test_Header = (Integer) parentContextMap.get("test_Header");
			}
			if (parentContextMap.containsKey("Extract4_File")) {
				context.Extract4_File = (String) parentContextMap.get("Extract4_File");
			}
			if (parentContextMap.containsKey("Extract4_Encoding")) {
				context.Extract4_Encoding = (String) parentContextMap.get("Extract4_Encoding");
			}
			if (parentContextMap.containsKey("Extract4_Header")) {
				context.Extract4_Header = (Integer) parentContextMap.get("Extract4_Header");
			}
			if (parentContextMap.containsKey("Extract4_DecimalSeparator")) {
				context.Extract4_DecimalSeparator = (String) parentContextMap.get("Extract4_DecimalSeparator");
			}
			if (parentContextMap.containsKey("Extract4_LastColumn")) {
				context.Extract4_LastColumn = (Integer) parentContextMap.get("Extract4_LastColumn");
			}
			if (parentContextMap.containsKey("Extract4_ThousandSeparator")) {
				context.Extract4_ThousandSeparator = (String) parentContextMap.get("Extract4_ThousandSeparator");
			}
			if (parentContextMap.containsKey("Extract4_FirstColumn")) {
				context.Extract4_FirstColumn = (Integer) parentContextMap.get("Extract4_FirstColumn");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputExcel_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_2) {
			globalMap.put("tFileInputExcel_2_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_2.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Base_data_orange");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 349074 characters generated by Talend Open Studio for Data Integration on the
 * 13 mai 2022 à 22:18:01 CEST
 ************************************************************************************************/