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

package orange.base_mobile_orange_0_1;

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
 * Job: Base_mobile_orange Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Base_mobile_orange implements TalendJob {

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

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Base_mobile_orange";
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
					Base_mobile_orange.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Base_mobile_orange.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_16_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_17_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_18_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_19_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_21_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_22_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_23_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_25_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_26_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row13_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row21_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row15_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class orangeStruct implements routines.system.IPersistableRow<orangeStruct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];

		public int gouvernorat_id;

		public int getGouvernorat_id() {
			return this.gouvernorat_id;
		}

		public int raison_sociale_id;

		public int getRaison_sociale_id() {
			return this.raison_sociale_id;
		}

		public java.util.Date date_activation_id;

		public java.util.Date getDate_activation_id() {
			return this.date_activation_id;
		}

		public int type_engagement_id;

		public int getType_engagement_id() {
			return this.type_engagement_id;
		}

		public Integer Extension_id;

		public Integer getExtension_id() {
			return this.Extension_id;
		}

		public Integer statut_finale_id;

		public Integer getStatut_finale_id() {
			return this.statut_finale_id;
		}

		public Integer nature_offre_id;

		public Integer getNature_offre_id() {
			return this.nature_offre_id;
		}

		public Integer offre_id;

		public Integer getOffre_id() {
			return this.offre_id;
		}

		public Integer Dim_Reference_OSM_Opportunite_id;

		public Integer getDim_Reference_OSM_Opportunite_id() {
			return this.Dim_Reference_OSM_Opportunite_id;
		}

		public Integer phase_opportunite_id;

		public Integer getPhase_opportunite_id() {
			return this.phase_opportunite_id;
		}

		public Integer membre_equipe_id;

		public Integer getMembre_equipe_id() {
			return this.membre_equipe_id;
		}

		public Integer equipe_id;

		public Integer getEquipe_id() {
			return this.equipe_id;
		}

		public java.util.Date DIM_Date_id;

		public java.util.Date getDIM_Date_id() {
			return this.DIM_Date_id;
		}

		public Float Quantite;

		public Float getQuantite() {
			return this.Quantite;
		}

		public Float Total_remise_HT;

		public Float getTotal_remise_HT() {
			return this.Total_remise_HT;
		}

		public String Duree_d_engagement__Opportunite;

		public String getDuree_d_engagement__Opportunite() {
			return this.Duree_d_engagement__Opportunite;
		}

		public Float Prix_unitaire;

		public Float getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public Float Remise______Opportunite;

		public Float getRemise______Opportunite() {
			return this.Remise______Opportunite;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.gouvernorat_id = dis.readInt();

					this.raison_sociale_id = dis.readInt();

					this.date_activation_id = readDate(dis);

					this.type_engagement_id = dis.readInt();

					this.Extension_id = readInteger(dis);

					this.statut_finale_id = readInteger(dis);

					this.nature_offre_id = readInteger(dis);

					this.offre_id = readInteger(dis);

					this.Dim_Reference_OSM_Opportunite_id = readInteger(dis);

					this.phase_opportunite_id = readInteger(dis);

					this.membre_equipe_id = readInteger(dis);

					this.equipe_id = readInteger(dis);

					this.DIM_Date_id = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Duree_d_engagement__Opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.gouvernorat_id = dis.readInt();

					this.raison_sociale_id = dis.readInt();

					this.date_activation_id = readDate(dis);

					this.type_engagement_id = dis.readInt();

					this.Extension_id = readInteger(dis);

					this.statut_finale_id = readInteger(dis);

					this.nature_offre_id = readInteger(dis);

					this.offre_id = readInteger(dis);

					this.Dim_Reference_OSM_Opportunite_id = readInteger(dis);

					this.phase_opportunite_id = readInteger(dis);

					this.membre_equipe_id = readInteger(dis);

					this.equipe_id = readInteger(dis);

					this.DIM_Date_id = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Quantite = null;
					} else {
						this.Quantite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Duree_d_engagement__Opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Prix_unitaire = null;
					} else {
						this.Prix_unitaire = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.gouvernorat_id);

				// int

				dos.writeInt(this.raison_sociale_id);

				// java.util.Date

				writeDate(this.date_activation_id, dos);

				// int

				dos.writeInt(this.type_engagement_id);

				// Integer

				writeInteger(this.Extension_id, dos);

				// Integer

				writeInteger(this.statut_finale_id, dos);

				// Integer

				writeInteger(this.nature_offre_id, dos);

				// Integer

				writeInteger(this.offre_id, dos);

				// Integer

				writeInteger(this.Dim_Reference_OSM_Opportunite_id, dos);

				// Integer

				writeInteger(this.phase_opportunite_id, dos);

				// Integer

				writeInteger(this.membre_equipe_id, dos);

				// Integer

				writeInteger(this.equipe_id, dos);

				// java.util.Date

				writeDate(this.DIM_Date_id, dos);

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.gouvernorat_id);

				// int

				dos.writeInt(this.raison_sociale_id);

				// java.util.Date

				writeDate(this.date_activation_id, dos);

				// int

				dos.writeInt(this.type_engagement_id);

				// Integer

				writeInteger(this.Extension_id, dos);

				// Integer

				writeInteger(this.statut_finale_id, dos);

				// Integer

				writeInteger(this.nature_offre_id, dos);

				// Integer

				writeInteger(this.offre_id, dos);

				// Integer

				writeInteger(this.Dim_Reference_OSM_Opportunite_id, dos);

				// Integer

				writeInteger(this.phase_opportunite_id, dos);

				// Integer

				writeInteger(this.membre_equipe_id, dos);

				// Integer

				writeInteger(this.equipe_id, dos);

				// java.util.Date

				writeDate(this.DIM_Date_id, dos);

				// Float

				if (this.Quantite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Quantite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// Float

				if (this.Prix_unitaire == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Prix_unitaire);
				}

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("gouvernorat_id=" + String.valueOf(gouvernorat_id));
			sb.append(",raison_sociale_id=" + String.valueOf(raison_sociale_id));
			sb.append(",date_activation_id=" + String.valueOf(date_activation_id));
			sb.append(",type_engagement_id=" + String.valueOf(type_engagement_id));
			sb.append(",Extension_id=" + String.valueOf(Extension_id));
			sb.append(",statut_finale_id=" + String.valueOf(statut_finale_id));
			sb.append(",nature_offre_id=" + String.valueOf(nature_offre_id));
			sb.append(",offre_id=" + String.valueOf(offre_id));
			sb.append(",Dim_Reference_OSM_Opportunite_id=" + String.valueOf(Dim_Reference_OSM_Opportunite_id));
			sb.append(",phase_opportunite_id=" + String.valueOf(phase_opportunite_id));
			sb.append(",membre_equipe_id=" + String.valueOf(membre_equipe_id));
			sb.append(",equipe_id=" + String.valueOf(equipe_id));
			sb.append(",DIM_Date_id=" + String.valueOf(DIM_Date_id));
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Total_remise_HT=" + String.valueOf(Total_remise_HT));
			sb.append(",Duree_d_engagement__Opportunite=" + Duree_d_engagement__Opportunite);
			sb.append(",Prix_unitaire=" + String.valueOf(Prix_unitaire));
			sb.append(",Remise______Opportunite=" + String.valueOf(Remise______Opportunite));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(orangeStruct other) {

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

	public static class correction1Struct implements routines.system.IPersistableRow<correction1Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];

		public String Reference_OSM_mere__Opportunite;

		public String getReference_OSM_mere__Opportunite() {
			return this.Reference_OSM_mere__Opportunite;
		}

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		public String Client_Prospect__Opportunite;

		public String getClient_Prospect__Opportunite() {
			return this.Client_Prospect__Opportunite;
		}

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		public String Produit_existant;

		public String getProduit_existant() {
			return this.Produit_existant;
		}

		public String Nature_produit__Produit_existant;

		public String getNature_produit__Produit_existant() {
			return this.Nature_produit__Produit_existant;
		}

		public Float Quantite;

		public Float getQuantite() {
			return this.Quantite;
		}

		public Float Prix_unitaire;

		public Float getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public Float Total_final;

		public Float getTotal_final() {
			return this.Total_final;
		}

		public Float Remise______Opportunite;

		public Float getRemise______Opportunite() {
			return this.Remise______Opportunite;
		}

		public Float Total_remise_HT;

		public Float getTotal_remise_HT() {
			return this.Total_remise_HT;
		}

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		public Float Budget_a_valider__Opportunite;

		public Float getBudget_a_valider__Opportunite() {
			return this.Budget_a_valider__Opportunite;
		}

		public Float Budget_a_valider_Extension__Opportunite;

		public Float getBudget_a_valider_Extension__Opportunite() {
			return this.Budget_a_valider_Extension__Opportunite;
		}

		public String Duree_d_engagement__Opportunite;

		public String getDuree_d_engagement__Opportunite() {
			return this.Duree_d_engagement__Opportunite;
		}

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		public String Demandeur__Opportunite;

		public String getDemandeur__Opportunite() {
			return this.Demandeur__Opportunite;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public java.util.Date Cree_le__Opportunite;

		public java.util.Date getCree_le__Opportunite() {
			return this.Cree_le__Opportunite;
		}

		public Integer Mois_de_creation;

		public Integer getMois_de_creation() {
			return this.Mois_de_creation;
		}

		public Integer Annee_de_creation;

		public Integer getAnnee_de_creation() {
			return this.Annee_de_creation;
		}

		public String Mois_et_annee_de_creation;

		public String getMois_et_annee_de_creation() {
			return this.Mois_et_annee_de_creation;
		}

		public String Raison_de_statut_OSM__Opportunite;

		public String getRaison_de_statut_OSM__Opportunite() {
			return this.Raison_de_statut_OSM__Opportunite;
		}

		public String Statut_OSM__Opportunite;

		public String getStatut_OSM__Opportunite() {
			return this.Statut_OSM__Opportunite;
		}

		public String Statut_activation__Commande;

		public String getStatut_activation__Commande() {
			return this.Statut_activation__Commande;
		}

		public java.util.Date Date_d_activation__Commande;

		public java.util.Date getDate_d_activation__Commande() {
			return this.Date_d_activation__Commande;
		}

		public Integer Mois_d_activation;

		public Integer getMois_d_activation() {
			return this.Mois_d_activation;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		public String Gouvernorat;

		public String getGouvernorat() {
			return this.Gouvernorat;
		}

		public String Phase_de_l_opportunite__Opportunite;

		public String getPhase_de_l_opportunite__Opportunite() {
			return this.Phase_de_l_opportunite__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

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
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider__Opportunite = null;
					} else {
						this.Budget_a_valider__Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider_Extension__Opportunite = null;
					} else {
						this.Budget_a_valider_Extension__Opportunite = dis.readFloat();
					}

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readDate(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readDate(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

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
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider__Opportunite = null;
					} else {
						this.Budget_a_valider__Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider_Extension__Opportunite = null;
					} else {
						this.Budget_a_valider_Extension__Opportunite = dis.readFloat();
					}

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readDate(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readDate(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

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

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// Float

				if (this.Budget_a_valider__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider__Opportunite);
				}

				// Float

				if (this.Budget_a_valider_Extension__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider_Extension__Opportunite);
				}

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// java.util.Date

				writeDate(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// java.util.Date

				writeDate(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

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

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// Float

				if (this.Budget_a_valider__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider__Opportunite);
				}

				// Float

				if (this.Budget_a_valider_Extension__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider_Extension__Opportunite);
				}

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// java.util.Date

				writeDate(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// java.util.Date

				writeDate(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Reference_OSM_mere__Opportunite=" + Reference_OSM_mere__Opportunite);
			sb.append(",Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append(",Client_Prospect__Opportunite=" + Client_Prospect__Opportunite);
			sb.append(",Offre=" + Offre);
			sb.append(",Nature_Offre=" + Nature_Offre);
			sb.append(",Produit_existant=" + Produit_existant);
			sb.append(",Nature_produit__Produit_existant=" + Nature_produit__Produit_existant);
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Prix_unitaire=" + String.valueOf(Prix_unitaire));
			sb.append(",Total_final=" + String.valueOf(Total_final));
			sb.append(",Remise______Opportunite=" + String.valueOf(Remise______Opportunite));
			sb.append(",Total_remise_HT=" + String.valueOf(Total_remise_HT));
			sb.append(",Extension__Opportunite=" + Extension__Opportunite);
			sb.append(",Budget_a_valider__Opportunite=" + String.valueOf(Budget_a_valider__Opportunite));
			sb.append(",Budget_a_valider_Extension__Opportunite="
					+ String.valueOf(Budget_a_valider_Extension__Opportunite));
			sb.append(",Duree_d_engagement__Opportunite=" + Duree_d_engagement__Opportunite);
			sb.append(",Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append(",Demandeur__Opportunite=" + Demandeur__Opportunite);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Cree_le__Opportunite=" + String.valueOf(Cree_le__Opportunite));
			sb.append(",Mois_de_creation=" + String.valueOf(Mois_de_creation));
			sb.append(",Annee_de_creation=" + String.valueOf(Annee_de_creation));
			sb.append(",Mois_et_annee_de_creation=" + Mois_et_annee_de_creation);
			sb.append(",Raison_de_statut_OSM__Opportunite=" + Raison_de_statut_OSM__Opportunite);
			sb.append(",Statut_OSM__Opportunite=" + Statut_OSM__Opportunite);
			sb.append(",Statut_activation__Commande=" + Statut_activation__Commande);
			sb.append(",Date_d_activation__Commande=" + String.valueOf(Date_d_activation__Commande));
			sb.append(",Mois_d_activation=" + String.valueOf(Mois_d_activation));
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append(",Gouvernorat=" + Gouvernorat);
			sb.append(",Phase_de_l_opportunite__Opportunite=" + Phase_de_l_opportunite__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(correction1Struct other) {

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
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];

		public String Reference_OSM_mere__Opportunite;

		public String getReference_OSM_mere__Opportunite() {
			return this.Reference_OSM_mere__Opportunite;
		}

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		public String Client_Prospect__Opportunite;

		public String getClient_Prospect__Opportunite() {
			return this.Client_Prospect__Opportunite;
		}

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		public String Produit_existant;

		public String getProduit_existant() {
			return this.Produit_existant;
		}

		public String Nature_produit__Produit_existant;

		public String getNature_produit__Produit_existant() {
			return this.Nature_produit__Produit_existant;
		}

		public Float Quantite;

		public Float getQuantite() {
			return this.Quantite;
		}

		public Float Prix_unitaire;

		public Float getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public Float Total_final;

		public Float getTotal_final() {
			return this.Total_final;
		}

		public Float Remise______Opportunite;

		public Float getRemise______Opportunite() {
			return this.Remise______Opportunite;
		}

		public Float Total_remise_HT;

		public Float getTotal_remise_HT() {
			return this.Total_remise_HT;
		}

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		public Float Budget_a_valider__Opportunite;

		public Float getBudget_a_valider__Opportunite() {
			return this.Budget_a_valider__Opportunite;
		}

		public Float Budget_a_valider_Extension__Opportunite;

		public Float getBudget_a_valider_Extension__Opportunite() {
			return this.Budget_a_valider_Extension__Opportunite;
		}

		public String Duree_d_engagement__Opportunite;

		public String getDuree_d_engagement__Opportunite() {
			return this.Duree_d_engagement__Opportunite;
		}

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		public String Demandeur__Opportunite;

		public String getDemandeur__Opportunite() {
			return this.Demandeur__Opportunite;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public java.util.Date Cree_le__Opportunite;

		public java.util.Date getCree_le__Opportunite() {
			return this.Cree_le__Opportunite;
		}

		public Integer Mois_de_creation;

		public Integer getMois_de_creation() {
			return this.Mois_de_creation;
		}

		public Integer Annee_de_creation;

		public Integer getAnnee_de_creation() {
			return this.Annee_de_creation;
		}

		public String Mois_et_annee_de_creation;

		public String getMois_et_annee_de_creation() {
			return this.Mois_et_annee_de_creation;
		}

		public String Raison_de_statut_OSM__Opportunite;

		public String getRaison_de_statut_OSM__Opportunite() {
			return this.Raison_de_statut_OSM__Opportunite;
		}

		public String Statut_OSM__Opportunite;

		public String getStatut_OSM__Opportunite() {
			return this.Statut_OSM__Opportunite;
		}

		public String Statut_activation__Commande;

		public String getStatut_activation__Commande() {
			return this.Statut_activation__Commande;
		}

		public java.util.Date Date_d_activation__Commande;

		public java.util.Date getDate_d_activation__Commande() {
			return this.Date_d_activation__Commande;
		}

		public Integer Mois_d_activation;

		public Integer getMois_d_activation() {
			return this.Mois_d_activation;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		public String Gouvernorat;

		public String getGouvernorat() {
			return this.Gouvernorat;
		}

		public String Phase_de_l_opportunite__Opportunite;

		public String getPhase_de_l_opportunite__Opportunite() {
			return this.Phase_de_l_opportunite__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

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
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider__Opportunite = null;
					} else {
						this.Budget_a_valider__Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider_Extension__Opportunite = null;
					} else {
						this.Budget_a_valider_Extension__Opportunite = dis.readFloat();
					}

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readDate(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readDate(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

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
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider__Opportunite = null;
					} else {
						this.Budget_a_valider__Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider_Extension__Opportunite = null;
					} else {
						this.Budget_a_valider_Extension__Opportunite = dis.readFloat();
					}

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readDate(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readDate(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

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

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// Float

				if (this.Budget_a_valider__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider__Opportunite);
				}

				// Float

				if (this.Budget_a_valider_Extension__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider_Extension__Opportunite);
				}

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// java.util.Date

				writeDate(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// java.util.Date

				writeDate(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

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

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// Float

				if (this.Budget_a_valider__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider__Opportunite);
				}

				// Float

				if (this.Budget_a_valider_Extension__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider_Extension__Opportunite);
				}

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// java.util.Date

				writeDate(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// java.util.Date

				writeDate(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Reference_OSM_mere__Opportunite=" + Reference_OSM_mere__Opportunite);
			sb.append(",Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append(",Client_Prospect__Opportunite=" + Client_Prospect__Opportunite);
			sb.append(",Offre=" + Offre);
			sb.append(",Nature_Offre=" + Nature_Offre);
			sb.append(",Produit_existant=" + Produit_existant);
			sb.append(",Nature_produit__Produit_existant=" + Nature_produit__Produit_existant);
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Prix_unitaire=" + String.valueOf(Prix_unitaire));
			sb.append(",Total_final=" + String.valueOf(Total_final));
			sb.append(",Remise______Opportunite=" + String.valueOf(Remise______Opportunite));
			sb.append(",Total_remise_HT=" + String.valueOf(Total_remise_HT));
			sb.append(",Extension__Opportunite=" + Extension__Opportunite);
			sb.append(",Budget_a_valider__Opportunite=" + String.valueOf(Budget_a_valider__Opportunite));
			sb.append(",Budget_a_valider_Extension__Opportunite="
					+ String.valueOf(Budget_a_valider_Extension__Opportunite));
			sb.append(",Duree_d_engagement__Opportunite=" + Duree_d_engagement__Opportunite);
			sb.append(",Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append(",Demandeur__Opportunite=" + Demandeur__Opportunite);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Cree_le__Opportunite=" + String.valueOf(Cree_le__Opportunite));
			sb.append(",Mois_de_creation=" + String.valueOf(Mois_de_creation));
			sb.append(",Annee_de_creation=" + String.valueOf(Annee_de_creation));
			sb.append(",Mois_et_annee_de_creation=" + Mois_et_annee_de_creation);
			sb.append(",Raison_de_statut_OSM__Opportunite=" + Raison_de_statut_OSM__Opportunite);
			sb.append(",Statut_OSM__Opportunite=" + Statut_OSM__Opportunite);
			sb.append(",Statut_activation__Commande=" + Statut_activation__Commande);
			sb.append(",Date_d_activation__Commande=" + String.valueOf(Date_d_activation__Commande));
			sb.append(",Mois_d_activation=" + String.valueOf(Mois_d_activation));
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append(",Gouvernorat=" + Gouvernorat);
			sb.append(",Phase_de_l_opportunite__Opportunite=" + Phase_de_l_opportunite__Opportunite);
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

	public static class after_tFileInputExcel_1Struct
			implements routines.system.IPersistableRow<after_tFileInputExcel_1Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];

		public String Reference_OSM_mere__Opportunite;

		public String getReference_OSM_mere__Opportunite() {
			return this.Reference_OSM_mere__Opportunite;
		}

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		public String Client_Prospect__Opportunite;

		public String getClient_Prospect__Opportunite() {
			return this.Client_Prospect__Opportunite;
		}

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		public String Produit_existant;

		public String getProduit_existant() {
			return this.Produit_existant;
		}

		public String Nature_produit__Produit_existant;

		public String getNature_produit__Produit_existant() {
			return this.Nature_produit__Produit_existant;
		}

		public Float Quantite;

		public Float getQuantite() {
			return this.Quantite;
		}

		public Float Prix_unitaire;

		public Float getPrix_unitaire() {
			return this.Prix_unitaire;
		}

		public Float Total_final;

		public Float getTotal_final() {
			return this.Total_final;
		}

		public Float Remise______Opportunite;

		public Float getRemise______Opportunite() {
			return this.Remise______Opportunite;
		}

		public Float Total_remise_HT;

		public Float getTotal_remise_HT() {
			return this.Total_remise_HT;
		}

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		public Float Budget_a_valider__Opportunite;

		public Float getBudget_a_valider__Opportunite() {
			return this.Budget_a_valider__Opportunite;
		}

		public Float Budget_a_valider_Extension__Opportunite;

		public Float getBudget_a_valider_Extension__Opportunite() {
			return this.Budget_a_valider_Extension__Opportunite;
		}

		public String Duree_d_engagement__Opportunite;

		public String getDuree_d_engagement__Opportunite() {
			return this.Duree_d_engagement__Opportunite;
		}

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		public String Demandeur__Opportunite;

		public String getDemandeur__Opportunite() {
			return this.Demandeur__Opportunite;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public java.util.Date Cree_le__Opportunite;

		public java.util.Date getCree_le__Opportunite() {
			return this.Cree_le__Opportunite;
		}

		public Integer Mois_de_creation;

		public Integer getMois_de_creation() {
			return this.Mois_de_creation;
		}

		public Integer Annee_de_creation;

		public Integer getAnnee_de_creation() {
			return this.Annee_de_creation;
		}

		public String Mois_et_annee_de_creation;

		public String getMois_et_annee_de_creation() {
			return this.Mois_et_annee_de_creation;
		}

		public String Raison_de_statut_OSM__Opportunite;

		public String getRaison_de_statut_OSM__Opportunite() {
			return this.Raison_de_statut_OSM__Opportunite;
		}

		public String Statut_OSM__Opportunite;

		public String getStatut_OSM__Opportunite() {
			return this.Statut_OSM__Opportunite;
		}

		public String Statut_activation__Commande;

		public String getStatut_activation__Commande() {
			return this.Statut_activation__Commande;
		}

		public java.util.Date Date_d_activation__Commande;

		public java.util.Date getDate_d_activation__Commande() {
			return this.Date_d_activation__Commande;
		}

		public Integer Mois_d_activation;

		public Integer getMois_d_activation() {
			return this.Mois_d_activation;
		}

		public String Staut_Final;

		public String getStaut_Final() {
			return this.Staut_Final;
		}

		public String Gouvernorat;

		public String getGouvernorat() {
			return this.Gouvernorat;
		}

		public String Phase_de_l_opportunite__Opportunite;

		public String getPhase_de_l_opportunite__Opportunite() {
			return this.Phase_de_l_opportunite__Opportunite;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

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
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider__Opportunite = null;
					} else {
						this.Budget_a_valider__Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider_Extension__Opportunite = null;
					} else {
						this.Budget_a_valider_Extension__Opportunite = dis.readFloat();
					}

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readDate(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readDate(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Reference_OSM_mere__Opportunite = readString(dis);

					this.Reference_OSM__Opportunite = readString(dis);

					this.Client_Prospect__Opportunite = readString(dis);

					this.Offre = readString(dis);

					this.Nature_Offre = readString(dis);

					this.Produit_existant = readString(dis);

					this.Nature_produit__Produit_existant = readString(dis);

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
						this.Total_final = null;
					} else {
						this.Total_final = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Remise______Opportunite = null;
					} else {
						this.Remise______Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Total_remise_HT = null;
					} else {
						this.Total_remise_HT = dis.readFloat();
					}

					this.Extension__Opportunite = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider__Opportunite = null;
					} else {
						this.Budget_a_valider__Opportunite = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Budget_a_valider_Extension__Opportunite = null;
					} else {
						this.Budget_a_valider_Extension__Opportunite = dis.readFloat();
					}

					this.Duree_d_engagement__Opportunite = readString(dis);

					this.Type_d_engagement__Opportunite = readString(dis);

					this.Demandeur__Opportunite = readString(dis);

					this.Equipe = readString(dis);

					this.Cree_le__Opportunite = readDate(dis);

					this.Mois_de_creation = readInteger(dis);

					this.Annee_de_creation = readInteger(dis);

					this.Mois_et_annee_de_creation = readString(dis);

					this.Raison_de_statut_OSM__Opportunite = readString(dis);

					this.Statut_OSM__Opportunite = readString(dis);

					this.Statut_activation__Commande = readString(dis);

					this.Date_d_activation__Commande = readDate(dis);

					this.Mois_d_activation = readInteger(dis);

					this.Staut_Final = readString(dis);

					this.Gouvernorat = readString(dis);

					this.Phase_de_l_opportunite__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

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

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// Float

				if (this.Budget_a_valider__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider__Opportunite);
				}

				// Float

				if (this.Budget_a_valider_Extension__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider_Extension__Opportunite);
				}

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// java.util.Date

				writeDate(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// java.util.Date

				writeDate(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM_mere__Opportunite, dos);

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

				// String

				writeString(this.Client_Prospect__Opportunite, dos);

				// String

				writeString(this.Offre, dos);

				// String

				writeString(this.Nature_Offre, dos);

				// String

				writeString(this.Produit_existant, dos);

				// String

				writeString(this.Nature_produit__Produit_existant, dos);

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

				if (this.Total_final == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_final);
				}

				// Float

				if (this.Remise______Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Remise______Opportunite);
				}

				// Float

				if (this.Total_remise_HT == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Total_remise_HT);
				}

				// String

				writeString(this.Extension__Opportunite, dos);

				// Float

				if (this.Budget_a_valider__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider__Opportunite);
				}

				// Float

				if (this.Budget_a_valider_Extension__Opportunite == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Budget_a_valider_Extension__Opportunite);
				}

				// String

				writeString(this.Duree_d_engagement__Opportunite, dos);

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

				// String

				writeString(this.Demandeur__Opportunite, dos);

				// String

				writeString(this.Equipe, dos);

				// java.util.Date

				writeDate(this.Cree_le__Opportunite, dos);

				// Integer

				writeInteger(this.Mois_de_creation, dos);

				// Integer

				writeInteger(this.Annee_de_creation, dos);

				// String

				writeString(this.Mois_et_annee_de_creation, dos);

				// String

				writeString(this.Raison_de_statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_OSM__Opportunite, dos);

				// String

				writeString(this.Statut_activation__Commande, dos);

				// java.util.Date

				writeDate(this.Date_d_activation__Commande, dos);

				// Integer

				writeInteger(this.Mois_d_activation, dos);

				// String

				writeString(this.Staut_Final, dos);

				// String

				writeString(this.Gouvernorat, dos);

				// String

				writeString(this.Phase_de_l_opportunite__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Reference_OSM_mere__Opportunite=" + Reference_OSM_mere__Opportunite);
			sb.append(",Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append(",Client_Prospect__Opportunite=" + Client_Prospect__Opportunite);
			sb.append(",Offre=" + Offre);
			sb.append(",Nature_Offre=" + Nature_Offre);
			sb.append(",Produit_existant=" + Produit_existant);
			sb.append(",Nature_produit__Produit_existant=" + Nature_produit__Produit_existant);
			sb.append(",Quantite=" + String.valueOf(Quantite));
			sb.append(",Prix_unitaire=" + String.valueOf(Prix_unitaire));
			sb.append(",Total_final=" + String.valueOf(Total_final));
			sb.append(",Remise______Opportunite=" + String.valueOf(Remise______Opportunite));
			sb.append(",Total_remise_HT=" + String.valueOf(Total_remise_HT));
			sb.append(",Extension__Opportunite=" + Extension__Opportunite);
			sb.append(",Budget_a_valider__Opportunite=" + String.valueOf(Budget_a_valider__Opportunite));
			sb.append(",Budget_a_valider_Extension__Opportunite="
					+ String.valueOf(Budget_a_valider_Extension__Opportunite));
			sb.append(",Duree_d_engagement__Opportunite=" + Duree_d_engagement__Opportunite);
			sb.append(",Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append(",Demandeur__Opportunite=" + Demandeur__Opportunite);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Cree_le__Opportunite=" + String.valueOf(Cree_le__Opportunite));
			sb.append(",Mois_de_creation=" + String.valueOf(Mois_de_creation));
			sb.append(",Annee_de_creation=" + String.valueOf(Annee_de_creation));
			sb.append(",Mois_et_annee_de_creation=" + Mois_et_annee_de_creation);
			sb.append(",Raison_de_statut_OSM__Opportunite=" + Raison_de_statut_OSM__Opportunite);
			sb.append(",Statut_OSM__Opportunite=" + Statut_OSM__Opportunite);
			sb.append(",Statut_activation__Commande=" + Statut_activation__Commande);
			sb.append(",Date_d_activation__Commande=" + String.valueOf(Date_d_activation__Commande));
			sb.append(",Mois_d_activation=" + String.valueOf(Mois_d_activation));
			sb.append(",Staut_Final=" + Staut_Final);
			sb.append(",Gouvernorat=" + Gouvernorat);
			sb.append(",Phase_de_l_opportunite__Opportunite=" + Phase_de_l_opportunite__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputExcel_1Struct other) {

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

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

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
				tDBInput_6Process(globalMap);
				tDBInput_16Process(globalMap);
				tDBInput_17Process(globalMap);
				tDBInput_18Process(globalMap);
				tDBInput_19Process(globalMap);
				tDBInput_21Process(globalMap);
				tDBInput_22Process(globalMap);
				tDBInput_23Process(globalMap);
				tDBInput_25Process(globalMap);
				tDBInput_26Process(globalMap);

				row1Struct row1 = new row1Struct();
				correction1Struct correction1 = new correction1Struct();
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
						"enc:routine.encryption.key.v1:+9YUwi2eWDgPwX1ed0Kx16PA4QAxv61fyPo75l0o3NnlHn7tPaE=");

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
					tableName_tDBOutput_2 = "Fact_table_mobile";
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "].[" + "Fact_table_mobile";
				}
				int count_tDBOutput_2 = 0;

				String insert_tDBOutput_2 = "INSERT INTO [" + tableName_tDBOutput_2
						+ "] ([gouvernorat_id],[raison_sociale_id],[date_activation_id],[type_engagement_id],[Extension_id],[statut_finale_id],[nature_offre_id],[offre_id],[Dim_Reference_OSM_Opportunite_id],[phase_opportunite_id],[membre_equipe_id],[equipe_id],[DIM_Date_id],[Quantite],[Total_remise_HT],[Duree_d_engagement__Opportunite],[Prix_unitaire],[Remise______Opportunite]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "correction1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row13Struct> tHash_Lookup_row13 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row13Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row13Struct>) globalMap
						.get("tHash_Lookup_row13"));

				row13Struct row13HashKey = new row13Struct();
				row13Struct row13Default = new row13Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct> tHash_Lookup_row10 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct>) globalMap
						.get("tHash_Lookup_row10"));

				row10Struct row10HashKey = new row10Struct();
				row10Struct row10Default = new row10Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) globalMap
						.get("tHash_Lookup_row9"));

				row9Struct row9HashKey = new row9Struct();
				row9Struct row9Default = new row9Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) globalMap
						.get("tHash_Lookup_row8"));

				row8Struct row8HashKey = new row8Struct();
				row8Struct row8Default = new row8Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct>) globalMap
						.get("tHash_Lookup_row7"));

				row7Struct row7HashKey = new row7Struct();
				row7Struct row7Default = new row7Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) globalMap
						.get("tHash_Lookup_row5"));

				row5Struct row5HashKey = new row5Struct();
				row5Struct row5Default = new row5Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) globalMap
						.get("tHash_Lookup_row4"));

				row4Struct row4HashKey = new row4Struct();
				row4Struct row4Default = new row4Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) globalMap
						.get("tHash_Lookup_row3"));

				row3Struct row3HashKey = new row3Struct();
				row3Struct row3Default = new row3Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row21Struct> tHash_Lookup_row21 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row21Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row21Struct>) globalMap
						.get("tHash_Lookup_row21"));

				row21Struct row21HashKey = new row21Struct();
				row21Struct row21Default = new row21Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row15Struct> tHash_Lookup_row15 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row15Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row15Struct>) globalMap
						.get("tHash_Lookup_row15"));

				row15Struct row15HashKey = new row15Struct();
				row15Struct row15Default = new row15Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
						.get("tHash_Lookup_row2"));

				row2Struct row2HashKey = new row2Struct();
				row2Struct row2Default = new row2Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				orangeStruct orange_tmp = new orangeStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				correction1Struct correction1_tmp = new correction1Struct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:qRzJ99gvnjCKHSYKH16NF6zCZThEDgBgHlgYdQ==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				class RegexUtil_tFileInputExcel_1 {

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
				RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

				Object source_tFileInputExcel_1 = "C:/Users/DELL/Downloads/Copie_de_EXTRACT_OPPORTUNITES_MOBILE_BB_VF_090520222381_1ans.xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof String) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_1), password_tFileInputExcel_1,
									true);
				} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_1, password_tFileInputExcel_1);
				} else {
					workbook_tFileInputExcel_1 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					sheetList_tFileInputExcel_1
							.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "Source", false));
					if (sheetList_tFileInputExcel_1.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
						if (sheet_FilterNull_tFileInputExcel_1 != null
								&& sheetList_FilterNull_tFileInputExcel_1.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
						}
					}
					sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
					if (sheetList_tFileInputExcel_1.size() > 0) {
						int nb_line_tFileInputExcel_1 = 0;

						int begin_line_tFileInputExcel_1 = 1;

						int footer_input_tFileInputExcel_1 = 0;

						int end_line_tFileInputExcel_1 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							end_line_tFileInputExcel_1 += (sheet_tFileInputExcel_1.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
						int limit_tFileInputExcel_1 = -1;
						int start_column_tFileInputExcel_1 = 1 - 1;
						int end_column_tFileInputExcel_1 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
								.get(0);
						int rowCount_tFileInputExcel_1 = 0;
						int sheetIndex_tFileInputExcel_1 = 0;
						int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

							int emptyColumnCount_tFileInputExcel_1 = 0;

							if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
								break;
							}

							while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
								rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
								sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
										.get(++sheetIndex_tFileInputExcel_1);
								currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_1_CURRENT_SHEET", sheet_tFileInputExcel_1.getSheetName());
							if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
								row_tFileInputExcel_1 = sheet_tFileInputExcel_1
										.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
							}
							row1 = null;
							int tempRowLength_tFileInputExcel_1 = 31;

							int columnIndex_tFileInputExcel_1 = 0;

							String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
							int excel_end_column_tFileInputExcel_1;
							if (row_tFileInputExcel_1 == null) {
								excel_end_column_tFileInputExcel_1 = 0;
							} else {
								excel_end_column_tFileInputExcel_1 = row_tFileInputExcel_1.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_1;
							if (end_column_tFileInputExcel_1 == -1) {
								actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
							} else {
								actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > excel_end_column_tFileInputExcel_1
										? excel_end_column_tFileInputExcel_1
										: end_column_tFileInputExcel_1;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {
								if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1
											.getCell(i + start_column_tFileInputExcel_1);
									if (cell_tFileInputExcel_1 != null) {
										switch (cell_tFileInputExcel_1.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_1)) {
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1
														.format(cell_tFileInputExcel_1.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_1[i] = String
													.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_1)) {
													temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_1.getNumericCellValue());
													temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_1[i] = String
														.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_1[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_1[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_1[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_1[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_1 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Reference_OSM_mere__Opportunite";

									row1.Reference_OSM_mere__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Reference_OSM_mere__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Reference_OSM__Opportunite";

									row1.Reference_OSM__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Reference_OSM__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 2;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Client_Prospect__Opportunite";

									row1.Client_Prospect__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Client_Prospect__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 3;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Offre";

									row1.Offre = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Offre = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 4;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nature_Offre";

									row1.Nature_Offre = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Nature_Offre = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 5;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Produit_existant";

									row1.Produit_existant = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Produit_existant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 6;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Nature_produit__Produit_existant";

									row1.Nature_produit__Produit_existant = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Nature_produit__Produit_existant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 7;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Quantite";

									row1.Quantite = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Quantite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 8;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Prix_unitaire";

									row1.Prix_unitaire = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Prix_unitaire = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 9;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Total_final";

									row1.Total_final = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Total_final = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 10;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Remise______Opportunite";

									row1.Remise______Opportunite = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Remise______Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 11;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Total_remise_HT";

									row1.Total_remise_HT = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Total_remise_HT = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 12;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Extension__Opportunite";

									row1.Extension__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Extension__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 13;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Budget_a_valider__Opportunite";

									row1.Budget_a_valider__Opportunite = ParserUtils.parseTo_Float(ParserUtils
											.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1],
													null, '.' == decimalChar_tFileInputExcel_1 ? null
															: decimalChar_tFileInputExcel_1));
								} else {
									row1.Budget_a_valider__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 14;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Budget_a_valider_Extension__Opportunite";

									row1.Budget_a_valider_Extension__Opportunite = ParserUtils.parseTo_Float(ParserUtils
											.parseTo_Number(temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1],
													null, '.' == decimalChar_tFileInputExcel_1 ? null
															: decimalChar_tFileInputExcel_1));
								} else {
									row1.Budget_a_valider_Extension__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 15;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Duree_d_engagement__Opportunite";

									row1.Duree_d_engagement__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Duree_d_engagement__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 16;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Type_d_engagement__Opportunite";

									row1.Type_d_engagement__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Type_d_engagement__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 17;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Demandeur__Opportunite";

									row1.Demandeur__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Demandeur__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 18;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Equipe";

									row1.Equipe = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Equipe = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 19;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Cree_le__Opportunite";

									if (19 < actual_end_column_tFileInputExcel_1) {
										try {
											if (row_tFileInputExcel_1
													.getCell(columnIndex_tFileInputExcel_1
															+ start_column_tFileInputExcel_1)
													.getCellType() == org.apache.poi.ss.usermodel.CellType.NUMERIC
													&& org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(
															row_tFileInputExcel_1.getCell(columnIndex_tFileInputExcel_1
																	+ start_column_tFileInputExcel_1))) {
												row1.Cree_le__Opportunite = row_tFileInputExcel_1.getCell(
														columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1)
														.getDateCellValue();
											} else {
												java.util.Date tempDate_tFileInputExcel_1 = ParserUtils.parseTo_Date(
														temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1],
														"dd-MM-yyyy");
												if (tempDate_tFileInputExcel_1
														.after((new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS"))
																.parse("9999/12/31 23:59:59.999"))
														|| tempDate_tFileInputExcel_1
																.before((new SimpleDateFormat("yyyy/MM/dd"))
																		.parse("1900/01/01"))) {
													throw new RuntimeException("The cell format is not Date in ( Row. "
															+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
															+ curColNum_tFileInputExcel_1 + " )");
												} else {
													row1.Cree_le__Opportunite = tempDate_tFileInputExcel_1;
												}
											}
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_1 + " )");
										}
									}

								} else {
									row1.Cree_le__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 20;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Mois_de_creation";

									row1.Mois_de_creation = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Mois_de_creation = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 21;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Annee_de_creation";

									row1.Annee_de_creation = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Annee_de_creation = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 22;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Mois_et_annee_de_creation";

									row1.Mois_et_annee_de_creation = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Mois_et_annee_de_creation = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 23;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Raison_de_statut_OSM__Opportunite";

									row1.Raison_de_statut_OSM__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Raison_de_statut_OSM__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 24;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Statut_OSM__Opportunite";

									row1.Statut_OSM__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Statut_OSM__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 25;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Statut_activation__Commande";

									row1.Statut_activation__Commande = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Statut_activation__Commande = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 26;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Date_d_activation__Commande";

									if (26 < actual_end_column_tFileInputExcel_1) {
										try {
											if (row_tFileInputExcel_1
													.getCell(columnIndex_tFileInputExcel_1
															+ start_column_tFileInputExcel_1)
													.getCellType() == org.apache.poi.ss.usermodel.CellType.NUMERIC
													&& org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(
															row_tFileInputExcel_1.getCell(columnIndex_tFileInputExcel_1
																	+ start_column_tFileInputExcel_1))) {
												row1.Date_d_activation__Commande = row_tFileInputExcel_1.getCell(
														columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1)
														.getDateCellValue();
											} else {
												java.util.Date tempDate_tFileInputExcel_1 = ParserUtils.parseTo_Date(
														temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1],
														"dd-MM-yyyy");
												if (tempDate_tFileInputExcel_1
														.after((new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS"))
																.parse("9999/12/31 23:59:59.999"))
														|| tempDate_tFileInputExcel_1
																.before((new SimpleDateFormat("yyyy/MM/dd"))
																		.parse("1900/01/01"))) {
													throw new RuntimeException("The cell format is not Date in ( Row. "
															+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
															+ curColNum_tFileInputExcel_1 + " )");
												} else {
													row1.Date_d_activation__Commande = tempDate_tFileInputExcel_1;
												}
											}
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_1 + " )");
										}
									}

								} else {
									row1.Date_d_activation__Commande = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 27;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Mois_d_activation";

									row1.Mois_d_activation = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.Mois_d_activation = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 28;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Staut_Final";

									row1.Staut_Final = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Staut_Final = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 29;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Gouvernorat";

									row1.Gouvernorat = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Gouvernorat = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 30;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Phase_de_l_opportunite__Opportunite";

									row1.Phase_de_l_opportunite__Opportunite = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Phase_de_l_opportunite__Opportunite = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputExcel_1 begin ] stop
							 */

							/**
							 * [tFileInputExcel_1 main ] start
							 */

							currentComponent = "tFileInputExcel_1";

							tos_count_tFileInputExcel_1++;

							/**
							 * [tFileInputExcel_1 main ] stop
							 */

							/**
							 * [tFileInputExcel_1 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tMap_4 main ] start
								 */

								currentComponent = "tMap_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row1"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_4 = false;
								boolean mainRowRejected_tMap_4 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
									// ###############################
									// # Output tables

									correction1 = null;

// # Output table : 'correction1'
									correction1_tmp.Reference_OSM_mere__Opportunite = row1.Reference_OSM_mere__Opportunite;
									correction1_tmp.Reference_OSM__Opportunite = row1.Reference_OSM__Opportunite;
									correction1_tmp.Client_Prospect__Opportunite = row1.Client_Prospect__Opportunite;
									correction1_tmp.Offre = row1.Offre;
									correction1_tmp.Nature_Offre = row1.Nature_Offre;
									correction1_tmp.Produit_existant = row1.Produit_existant;
									correction1_tmp.Nature_produit__Produit_existant = row1.Nature_produit__Produit_existant;
									correction1_tmp.Quantite = row1.Quantite;
									correction1_tmp.Prix_unitaire = row1.Prix_unitaire;
									correction1_tmp.Total_final = row1.Total_final;
									correction1_tmp.Remise______Opportunite = row1.Remise______Opportunite;
									correction1_tmp.Total_remise_HT = row1.Total_remise_HT;
									correction1_tmp.Extension__Opportunite = row1.Extension__Opportunite;
									correction1_tmp.Budget_a_valider__Opportunite = row1.Budget_a_valider__Opportunite;
									correction1_tmp.Budget_a_valider_Extension__Opportunite = row1.Budget_a_valider_Extension__Opportunite;
									correction1_tmp.Duree_d_engagement__Opportunite = row1.Duree_d_engagement__Opportunite;
									correction1_tmp.Type_d_engagement__Opportunite = row1.Type_d_engagement__Opportunite;
									correction1_tmp.Demandeur__Opportunite = StringHandling
											.UPCASE(row1.Demandeur__Opportunite);
									correction1_tmp.Equipe = row1.Equipe;
									correction1_tmp.Cree_le__Opportunite = row1.Cree_le__Opportunite;
									correction1_tmp.Mois_de_creation = row1.Mois_de_creation;
									correction1_tmp.Annee_de_creation = row1.Annee_de_creation;
									correction1_tmp.Mois_et_annee_de_creation = row1.Mois_et_annee_de_creation;
									correction1_tmp.Raison_de_statut_OSM__Opportunite = row1.Raison_de_statut_OSM__Opportunite;
									correction1_tmp.Statut_OSM__Opportunite = row1.Statut_OSM__Opportunite;
									correction1_tmp.Statut_activation__Commande = row1.Statut_activation__Commande;
									correction1_tmp.Date_d_activation__Commande = row1.Date_d_activation__Commande;
									correction1_tmp.Mois_d_activation = row1.Mois_d_activation;
									correction1_tmp.Staut_Final = row1.Staut_Final;
									correction1_tmp.Gouvernorat = StringHandling
											.TRIM(StringHandling.UPCASE(row1.Gouvernorat));
									correction1_tmp.Phase_de_l_opportunite__Opportunite = row1.Phase_de_l_opportunite__Opportunite;
									correction1 = correction1_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_4 = false;

								tos_count_tMap_4++;

								/**
								 * [tMap_4 main ] stop
								 */

								/**
								 * [tMap_4 process_data_begin ] start
								 */

								currentComponent = "tMap_4";

								/**
								 * [tMap_4 process_data_begin ] stop
								 */
// Start of branch "correction1"
								if (correction1 != null) {

									/**
									 * [tMap_1 main ] start
									 */

									currentComponent = "tMap_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "correction1"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_1 = false;
									boolean mainRowRejected_tMap_1 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row13"
									///////////////////////////////////////////////

									boolean forceLooprow13 = false;

									row13Struct row13ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row13HashKey.Type_d_engagement__Opportunite = correction1.Type_d_engagement__Opportunite;

										row13HashKey.hashCodeDirty = true;

										tHash_Lookup_row13.lookup(row13HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row13 != null && tHash_Lookup_row13.getCount(row13HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row13' and it contains more one result from keys :
										// row13.Type_d_engagement__Opportunite = '" +
										// row13HashKey.Type_d_engagement__Opportunite + "'");
									} // G 071

									row13Struct row13 = null;

									row13Struct fromLookup_row13 = null;
									row13 = row13Default;

									if (tHash_Lookup_row13 != null && tHash_Lookup_row13.hasNext()) { // G 099

										fromLookup_row13 = tHash_Lookup_row13.next();

									} // G 099

									if (fromLookup_row13 != null) {
										row13 = fromLookup_row13;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row10"
									///////////////////////////////////////////////

									boolean forceLooprow10 = false;

									row10Struct row10ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row10HashKey.Equipe = correction1.Equipe;

										row10HashKey.hashCodeDirty = true;

										tHash_Lookup_row10.lookup(row10HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row10 != null && tHash_Lookup_row10.getCount(row10HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row10' and it contains more one result from keys : row10.Equipe = '" +
										// row10HashKey.Equipe + "'");
									} // G 071

									row10Struct row10 = null;

									row10Struct fromLookup_row10 = null;
									row10 = row10Default;

									if (tHash_Lookup_row10 != null && tHash_Lookup_row10.hasNext()) { // G 099

										fromLookup_row10 = tHash_Lookup_row10.next();

									} // G 099

									if (fromLookup_row10 != null) {
										row10 = fromLookup_row10;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row9"
									///////////////////////////////////////////////

									boolean forceLooprow9 = false;

									row9Struct row9ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row9HashKey.Extension__Opportunite = correction1.Extension__Opportunite;

										row9HashKey.hashCodeDirty = true;

										tHash_Lookup_row9.lookup(row9HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row9 != null && tHash_Lookup_row9.getCount(row9HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row9'
										// and it contains more one result from keys : row9.Extension__Opportunite = '"
										// + row9HashKey.Extension__Opportunite + "'");
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

									///////////////////////////////////////////////
									// Starting Lookup Table "row8"
									///////////////////////////////////////////////

									boolean forceLooprow8 = false;

									row8Struct row8ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row8HashKey.Gouvernorat = correction1.Gouvernorat;

										row8HashKey.hashCodeDirty = true;

										tHash_Lookup_row8.lookup(row8HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row8 != null && tHash_Lookup_row8.getCount(row8HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row8'
										// and it contains more one result from keys : row8.Gouvernorat = '" +
										// row8HashKey.Gouvernorat + "'");
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
									// Starting Lookup Table "row7"
									///////////////////////////////////////////////

									boolean forceLooprow7 = false;

									row7Struct row7ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row7HashKey.Nature_Offre = correction1.Nature_Offre;

										row7HashKey.hashCodeDirty = true;

										tHash_Lookup_row7.lookup(row7HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row7 != null && tHash_Lookup_row7.getCount(row7HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row7'
										// and it contains more one result from keys : row7.Nature_Offre = '" +
										// row7HashKey.Nature_Offre + "'");
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
									// Starting Lookup Table "row5"
									///////////////////////////////////////////////

									boolean forceLooprow5 = false;

									row5Struct row5ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row5HashKey.Phase_de_l_opportunite = correction1.Phase_de_l_opportunite__Opportunite;

										row5HashKey.hashCodeDirty = true;

										tHash_Lookup_row5.lookup(row5HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row5 != null && tHash_Lookup_row5.getCount(row5HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row5'
										// and it contains more one result from keys : row5.Phase_de_l_opportunite = '"
										// + row5HashKey.Phase_de_l_opportunite + "'");
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

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row4HashKey.Client_Prospect = correction1.Client_Prospect__Opportunite;

										row4HashKey.hashCodeDirty = true;

										tHash_Lookup_row4.lookup(row4HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4'
										// and it contains more one result from keys : row4.Client_Prospect = '" +
										// row4HashKey.Client_Prospect + "'");
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
									// Starting Lookup Table "row3"
									///////////////////////////////////////////////

									boolean forceLooprow3 = false;

									row3Struct row3ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row3HashKey.Staut_Final = correction1.Staut_Final;

										row3HashKey.hashCodeDirty = true;

										tHash_Lookup_row3.lookup(row3HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3'
										// and it contains more one result from keys : row3.Staut_Final = '" +
										// row3HashKey.Staut_Final + "'");
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
									// Starting Lookup Table "row21"
									///////////////////////////////////////////////

									boolean forceLooprow21 = false;

									row21Struct row21ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row21HashKey.Account_Manager__Client_Prospect = correction1.Demandeur__Opportunite;

										row21HashKey.hashCodeDirty = true;

										tHash_Lookup_row21.lookup(row21HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row21 != null && tHash_Lookup_row21.getCount(row21HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row21' and it contains more one result from keys :
										// row21.Account_Manager__Client_Prospect = '" +
										// row21HashKey.Account_Manager__Client_Prospect + "'");
									} // G 071

									row21Struct row21 = null;

									row21Struct fromLookup_row21 = null;
									row21 = row21Default;

									if (tHash_Lookup_row21 != null && tHash_Lookup_row21.hasNext()) { // G 099

										fromLookup_row21 = tHash_Lookup_row21.next();

									} // G 099

									if (fromLookup_row21 != null) {
										row21 = fromLookup_row21;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row15"
									///////////////////////////////////////////////

									boolean forceLooprow15 = false;

									row15Struct row15ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row15HashKey.Reference_OSM__Opportunite = correction1.Reference_OSM__Opportunite;

										row15HashKey.hashCodeDirty = true;

										tHash_Lookup_row15.lookup(row15HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row15 != null && tHash_Lookup_row15.getCount(row15HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row15' and it contains more one result from keys :
										// row15.Reference_OSM__Opportunite = '" +
										// row15HashKey.Reference_OSM__Opportunite + "'");
									} // G 071

									row15Struct row15 = null;

									row15Struct fromLookup_row15 = null;
									row15 = row15Default;

									if (tHash_Lookup_row15 != null && tHash_Lookup_row15.hasNext()) { // G 099

										fromLookup_row15 = tHash_Lookup_row15.next();

									} // G 099

									if (fromLookup_row15 != null) {
										row15 = fromLookup_row15;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row2"
									///////////////////////////////////////////////

									boolean forceLooprow2 = false;

									row2Struct row2ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_1 = false;

										row2HashKey.Offre = correction1.Offre;

										row2HashKey.hashCodeDirty = true;

										tHash_Lookup_row2.lookup(row2HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2'
										// and it contains more one result from keys : row2.Offre = '" +
										// row2HashKey.Offre + "'");
									} // G 071

									row2Struct row2 = null;

									row2Struct fromLookup_row2 = null;
									row2 = row2Default;

									if (tHash_Lookup_row2 != null && tHash_Lookup_row2.hasNext()) { // G 099

										fromLookup_row2 = tHash_Lookup_row2.next();

									} // G 099

									if (fromLookup_row2 != null) {
										row2 = fromLookup_row2;
									}

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
										// ###############################
										// # Output tables

										orange = null;

// # Output table : 'orange'
										orange_tmp.gouvernorat_id = row8.gouvernorat_id;
										orange_tmp.raison_sociale_id = row4.Client_Prospect_id;
										orange_tmp.date_activation_id = correction1.Date_d_activation__Commande;
										orange_tmp.type_engagement_id = row13.type_engagement_id;
										orange_tmp.Extension_id = row9.Extension_id;
										orange_tmp.statut_finale_id = row3.statu_final_id;
										orange_tmp.nature_offre_id = row7.nature_offre_id;
										orange_tmp.offre_id = row2.offre_id;
										orange_tmp.Dim_Reference_OSM_Opportunite_id = row15.Dim_Reference_OSM_Opportunite_id;
										orange_tmp.phase_opportunite_id = row5.phase_de_l_opportunite_id;
										orange_tmp.membre_equipe_id = row21.membre_equipe_id;
										orange_tmp.equipe_id = row10.equipe_id;
										orange_tmp.DIM_Date_id = correction1.Cree_le__Opportunite;
										orange_tmp.Quantite = correction1.Quantite;
										orange_tmp.Total_remise_HT = correction1.Total_remise_HT;
										orange_tmp.Duree_d_engagement__Opportunite = correction1.Duree_d_engagement__Opportunite;
										orange_tmp.Prix_unitaire = correction1.Prix_unitaire;
										orange_tmp.Remise______Opportunite = correction1.Remise______Opportunite;
										orange = orange_tmp;
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_1 = false;

									tos_count_tMap_1++;

									/**
									 * [tMap_1 main ] stop
									 */

									/**
									 * [tMap_1 process_data_begin ] start
									 */

									currentComponent = "tMap_1";

									/**
									 * [tMap_1 process_data_begin ] stop
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
										pstmt_tDBOutput_2.setInt(1, orange.gouvernorat_id);

										pstmt_tDBOutput_2.setInt(2, orange.raison_sociale_id);

										if (orange.date_activation_id != null) {
											pstmt_tDBOutput_2.setTimestamp(3,
													new java.sql.Timestamp(orange.date_activation_id.getTime()));
										} else {
											pstmt_tDBOutput_2.setNull(3, java.sql.Types.TIMESTAMP);
										}

										pstmt_tDBOutput_2.setInt(4, orange.type_engagement_id);

										if (orange.Extension_id == null) {
											pstmt_tDBOutput_2.setNull(5, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(5, orange.Extension_id);
										}

										if (orange.statut_finale_id == null) {
											pstmt_tDBOutput_2.setNull(6, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(6, orange.statut_finale_id);
										}

										if (orange.nature_offre_id == null) {
											pstmt_tDBOutput_2.setNull(7, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(7, orange.nature_offre_id);
										}

										if (orange.offre_id == null) {
											pstmt_tDBOutput_2.setNull(8, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(8, orange.offre_id);
										}

										if (orange.Dim_Reference_OSM_Opportunite_id == null) {
											pstmt_tDBOutput_2.setNull(9, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(9, orange.Dim_Reference_OSM_Opportunite_id);
										}

										if (orange.phase_opportunite_id == null) {
											pstmt_tDBOutput_2.setNull(10, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(10, orange.phase_opportunite_id);
										}

										if (orange.membre_equipe_id == null) {
											pstmt_tDBOutput_2.setNull(11, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(11, orange.membre_equipe_id);
										}

										if (orange.equipe_id == null) {
											pstmt_tDBOutput_2.setNull(12, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(12, orange.equipe_id);
										}

										if (orange.DIM_Date_id != null) {
											pstmt_tDBOutput_2.setTimestamp(13,
													new java.sql.Timestamp(orange.DIM_Date_id.getTime()));
										} else {
											pstmt_tDBOutput_2.setNull(13, java.sql.Types.TIMESTAMP);
										}

										if (orange.Quantite == null) {
											pstmt_tDBOutput_2.setNull(14, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(14, orange.Quantite);
										}

										if (orange.Total_remise_HT == null) {
											pstmt_tDBOutput_2.setNull(15, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(15, orange.Total_remise_HT);
										}

										if (orange.Duree_d_engagement__Opportunite == null) {
											pstmt_tDBOutput_2.setNull(16, java.sql.Types.VARCHAR);
										} else {
											pstmt_tDBOutput_2.setString(16, orange.Duree_d_engagement__Opportunite);
										}

										if (orange.Prix_unitaire == null) {
											pstmt_tDBOutput_2.setNull(17, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(17, orange.Prix_unitaire);
										}

										if (orange.Remise______Opportunite == null) {
											pstmt_tDBOutput_2.setNull(18, java.sql.Types.FLOAT);
										} else {
											pstmt_tDBOutput_2.setFloat(18, orange.Remise______Opportunite);
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
									 * [tMap_1 process_data_end ] start
									 */

									currentComponent = "tMap_1";

									/**
									 * [tMap_1 process_data_end ] stop
									 */

								} // End of branch "correction1"

								/**
								 * [tMap_4 process_data_end ] start
								 */

								currentComponent = "tMap_4";

								/**
								 * [tMap_4 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputExcel_1 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_1 end ] start
							 */

							currentComponent = "tFileInputExcel_1";

						}

						globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);

					}

				} finally {

					if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_1.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row13 != null) {
					tHash_Lookup_row13.endGet();
				}
				globalMap.remove("tHash_Lookup_row13");

				if (tHash_Lookup_row10 != null) {
					tHash_Lookup_row10.endGet();
				}
				globalMap.remove("tHash_Lookup_row10");

				if (tHash_Lookup_row9 != null) {
					tHash_Lookup_row9.endGet();
				}
				globalMap.remove("tHash_Lookup_row9");

				if (tHash_Lookup_row8 != null) {
					tHash_Lookup_row8.endGet();
				}
				globalMap.remove("tHash_Lookup_row8");

				if (tHash_Lookup_row7 != null) {
					tHash_Lookup_row7.endGet();
				}
				globalMap.remove("tHash_Lookup_row7");

				if (tHash_Lookup_row5 != null) {
					tHash_Lookup_row5.endGet();
				}
				globalMap.remove("tHash_Lookup_row5");

				if (tHash_Lookup_row4 != null) {
					tHash_Lookup_row4.endGet();
				}
				globalMap.remove("tHash_Lookup_row4");

				if (tHash_Lookup_row3 != null) {
					tHash_Lookup_row3.endGet();
				}
				globalMap.remove("tHash_Lookup_row3");

				if (tHash_Lookup_row21 != null) {
					tHash_Lookup_row21.endGet();
				}
				globalMap.remove("tHash_Lookup_row21");

				if (tHash_Lookup_row15 != null) {
					tHash_Lookup_row15.endGet();
				}
				globalMap.remove("tHash_Lookup_row15");

				if (tHash_Lookup_row2 != null) {
					tHash_Lookup_row2.endGet();
				}
				globalMap.remove("tHash_Lookup_row2");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "correction1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
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

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row2");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row13");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row10");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row9");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row8");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row7");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row5");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row4");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row3");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row21");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row15");

			try {

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
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

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int offre_id;

		public int getOffre_id() {
			return this.offre_id;
		}

		public String Offre;

		public String getOffre() {
			return this.Offre;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Offre == null) ? 0 : this.Offre.hashCode());

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
			final row2Struct other = (row2Struct) obj;

			if (this.Offre == null) {
				if (other.Offre != null)
					return false;

			} else if (!this.Offre.equals(other.Offre))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.offre_id = this.offre_id;
			other.Offre = this.Offre;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.Offre = this.Offre;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Offre, dos);

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

				this.offre_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.offre_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.offre_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.offre_id);

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
			sb.append("offre_id=" + String.valueOf(offre_id));
			sb.append(",Offre=" + Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Offre, other.Offre);
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

				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedHash_row2 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row2", false);
				start_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tAdvancedHash_row2 = 0;

				// connection name:row2
				// source node:tDBInput_1 - inputs:(after_tFileInputExcel_1) outputs:(row2,row2)
				// | target node:tAdvancedHash_row2 - inputs:(row2) outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedHash_row2 begin ] stop
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
						"enc:routine.encryption.key.v1:u7rji10Y9pxI+NAgfO6uobAWXGA3Ei6oRf+RuBpUl68rdIytbj8=");

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

				String dbquery_tDBInput_1 = "SELECT dbo.Dim_offre.offre_id,\n		dbo.Dim_offre.Offre\nFROM	dbo.Dim_offre";

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
							row2.offre_id = 0;
						} else {

							row2.offre_id = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row2.Offre = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row2.Offre = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row2.Offre = tmpContent_tDBInput_1;
								}
							} else {
								row2.Offre = null;
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
						 * [tAdvancedHash_row2 main ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row2"

							);
						}

						row2Struct row2_HashRow = new row2Struct();

						row2_HashRow.offre_id = row2.offre_id;

						row2_HashRow.Offre = row2.Offre;

						tHash_Lookup_row2.put(row2_HashRow);

						tos_count_tAdvancedHash_row2++;

						/**
						 * [tAdvancedHash_row2 main ] stop
						 */

						/**
						 * [tAdvancedHash_row2 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						/**
						 * [tAdvancedHash_row2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row2 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						/**
						 * [tAdvancedHash_row2 process_data_end ] stop
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
				 * [tAdvancedHash_row2 end ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				tHash_Lookup_row2.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tAdvancedHash_row2", true);
				end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row2 end ] stop
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
				 * [tAdvancedHash_row2 finally ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				/**
				 * [tAdvancedHash_row2 finally ] stop
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

	public static class row13Struct implements routines.system.IPersistableComparableLookupRow<row13Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int type_engagement_id;

		public int getType_engagement_id() {
			return this.type_engagement_id;
		}

		public String Type_d_engagement__Opportunite;

		public String getType_d_engagement__Opportunite() {
			return this.Type_d_engagement__Opportunite;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Type_d_engagement__Opportunite == null) ? 0
						: this.Type_d_engagement__Opportunite.hashCode());

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
			final row13Struct other = (row13Struct) obj;

			if (this.Type_d_engagement__Opportunite == null) {
				if (other.Type_d_engagement__Opportunite != null)
					return false;

			} else if (!this.Type_d_engagement__Opportunite.equals(other.Type_d_engagement__Opportunite))

				return false;

			return true;
		}

		public void copyDataTo(row13Struct other) {

			other.type_engagement_id = this.type_engagement_id;
			other.Type_d_engagement__Opportunite = this.Type_d_engagement__Opportunite;

		}

		public void copyKeysDataTo(row13Struct other) {

			other.Type_d_engagement__Opportunite = this.Type_d_engagement__Opportunite;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Type_d_engagement__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Type_d_engagement__Opportunite, dos);

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

				this.type_engagement_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.type_engagement_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.type_engagement_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.type_engagement_id);

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
			sb.append("type_engagement_id=" + String.valueOf(type_engagement_id));
			sb.append(",Type_d_engagement__Opportunite=" + Type_d_engagement__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row13Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Type_d_engagement__Opportunite,
					other.Type_d_engagement__Opportunite);
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

	public void tDBInput_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_6_SUBPROCESS_STATE", 0);

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

				row13Struct row13 = new row13Struct();

				/**
				 * [tAdvancedHash_row13 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row13", false);
				start_Hash.put("tAdvancedHash_row13", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row13";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row13");
				}

				int tos_count_tAdvancedHash_row13 = 0;

				// connection name:row13
				// source node:tDBInput_6 - inputs:(after_tFileInputExcel_1)
				// outputs:(row13,row13) | target node:tAdvancedHash_row13 - inputs:(row13)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row13 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row13Struct> tHash_Lookup_row13 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row13Struct>getLookup(matchingModeEnum_row13);

				globalMap.put("tHash_Lookup_row13", tHash_Lookup_row13);

				/**
				 * [tAdvancedHash_row13 begin ] stop
				 */

				/**
				 * [tDBInput_6 begin ] start
				 */

				ok_Hash.put("tDBInput_6", false);
				start_Hash.put("tDBInput_6", System.currentTimeMillis());

				currentComponent = "tDBInput_6";

				int tos_count_tDBInput_6 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_6 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_6 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_6 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_6, talendToDBArray_tDBInput_6);
				int nb_line_tDBInput_6 = 0;
				java.sql.Connection conn_tDBInput_6 = null;
				String driverClass_tDBInput_6 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_6 = java.lang.Class.forName(driverClass_tDBInput_6);
				String dbUser_tDBInput_6 = "sa";

				final String decryptedPassword_tDBInput_6 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:7Wgriv0h6TmtsVvqylcnC17hod6Fvz6h8GprrvV4FtWxmtJTGmM=");

				String dbPwd_tDBInput_6 = decryptedPassword_tDBInput_6;

				String port_tDBInput_6 = "1433";
				String dbname_tDBInput_6 = "orange_DW__";
				String url_tDBInput_6 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_6)) {
					url_tDBInput_6 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_6)) {
					url_tDBInput_6 += "//" + "orange_DW__";
				}
				url_tDBInput_6 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_6 = "dbo";

				conn_tDBInput_6 = java.sql.DriverManager.getConnection(url_tDBInput_6, dbUser_tDBInput_6,
						dbPwd_tDBInput_6);

				java.sql.Statement stmt_tDBInput_6 = conn_tDBInput_6.createStatement();

				String dbquery_tDBInput_6 = "SELECT dbo.Dim_type_engagement.type_engagement_id,\n		dbo.Dim_type_engagement.Type_d_engagement__Opportunite\nFROM	dbo.Di"
						+ "m_type_engagement";

				globalMap.put("tDBInput_6_QUERY", dbquery_tDBInput_6);
				java.sql.ResultSet rs_tDBInput_6 = null;

				try {
					rs_tDBInput_6 = stmt_tDBInput_6.executeQuery(dbquery_tDBInput_6);
					java.sql.ResultSetMetaData rsmd_tDBInput_6 = rs_tDBInput_6.getMetaData();
					int colQtyInRs_tDBInput_6 = rsmd_tDBInput_6.getColumnCount();

					String tmpContent_tDBInput_6 = null;

					while (rs_tDBInput_6.next()) {
						nb_line_tDBInput_6++;

						if (colQtyInRs_tDBInput_6 < 1) {
							row13.type_engagement_id = 0;
						} else {

							row13.type_engagement_id = rs_tDBInput_6.getInt(1);
							if (rs_tDBInput_6.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_6 < 2) {
							row13.Type_d_engagement__Opportunite = null;
						} else {

							tmpContent_tDBInput_6 = rs_tDBInput_6.getString(2);
							if (tmpContent_tDBInput_6 != null) {
								if (talendToDBList_tDBInput_6.contains(
										rsmd_tDBInput_6.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row13.Type_d_engagement__Opportunite = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_6);
								} else {
									row13.Type_d_engagement__Opportunite = tmpContent_tDBInput_6;
								}
							} else {
								row13.Type_d_engagement__Opportunite = null;
							}
						}

						/**
						 * [tDBInput_6 begin ] stop
						 */

						/**
						 * [tDBInput_6 main ] start
						 */

						currentComponent = "tDBInput_6";

						tos_count_tDBInput_6++;

						/**
						 * [tDBInput_6 main ] stop
						 */

						/**
						 * [tDBInput_6 process_data_begin ] start
						 */

						currentComponent = "tDBInput_6";

						/**
						 * [tDBInput_6 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row13 main ] start
						 */

						currentComponent = "tAdvancedHash_row13";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row13"

							);
						}

						row13Struct row13_HashRow = new row13Struct();

						row13_HashRow.type_engagement_id = row13.type_engagement_id;

						row13_HashRow.Type_d_engagement__Opportunite = row13.Type_d_engagement__Opportunite;

						tHash_Lookup_row13.put(row13_HashRow);

						tos_count_tAdvancedHash_row13++;

						/**
						 * [tAdvancedHash_row13 main ] stop
						 */

						/**
						 * [tAdvancedHash_row13 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row13";

						/**
						 * [tAdvancedHash_row13 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row13 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row13";

						/**
						 * [tAdvancedHash_row13 process_data_end ] stop
						 */

						/**
						 * [tDBInput_6 process_data_end ] start
						 */

						currentComponent = "tDBInput_6";

						/**
						 * [tDBInput_6 process_data_end ] stop
						 */

						/**
						 * [tDBInput_6 end ] start
						 */

						currentComponent = "tDBInput_6";

					}
				} finally {
					if (rs_tDBInput_6 != null) {
						rs_tDBInput_6.close();
					}
					if (stmt_tDBInput_6 != null) {
						stmt_tDBInput_6.close();
					}
					if (conn_tDBInput_6 != null && !conn_tDBInput_6.isClosed()) {

						conn_tDBInput_6.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_6_NB_LINE", nb_line_tDBInput_6);

				ok_Hash.put("tDBInput_6", true);
				end_Hash.put("tDBInput_6", System.currentTimeMillis());

				/**
				 * [tDBInput_6 end ] stop
				 */

				/**
				 * [tAdvancedHash_row13 end ] start
				 */

				currentComponent = "tAdvancedHash_row13";

				tHash_Lookup_row13.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row13");
				}

				ok_Hash.put("tAdvancedHash_row13", true);
				end_Hash.put("tAdvancedHash_row13", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row13 end ] stop
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
				 * [tDBInput_6 finally ] start
				 */

				currentComponent = "tDBInput_6";

				/**
				 * [tDBInput_6 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row13 finally ] start
				 */

				currentComponent = "tAdvancedHash_row13";

				/**
				 * [tAdvancedHash_row13 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_6_SUBPROCESS_STATE", 1);
	}

	public static class row10Struct implements routines.system.IPersistableComparableLookupRow<row10Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
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
			final row10Struct other = (row10Struct) obj;

			if (this.Equipe == null) {
				if (other.Equipe != null)
					return false;

			} else if (!this.Equipe.equals(other.Equipe))

				return false;

			return true;
		}

		public void copyDataTo(row10Struct other) {

			other.equipe_id = this.equipe_id;
			other.Equipe = this.Equipe;

		}

		public void copyKeysDataTo(row10Struct other) {

			other.Equipe = this.Equipe;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Equipe = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

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
		public int compareTo(row10Struct other) {

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

	public void tDBInput_16Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_16_SUBPROCESS_STATE", 0);

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

				row10Struct row10 = new row10Struct();

				/**
				 * [tAdvancedHash_row10 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row10", false);
				start_Hash.put("tAdvancedHash_row10", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row10";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row10");
				}

				int tos_count_tAdvancedHash_row10 = 0;

				// connection name:row10
				// source node:tDBInput_16 - inputs:(after_tFileInputExcel_1)
				// outputs:(row10,row10) | target node:tAdvancedHash_row10 - inputs:(row10)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row10 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct> tHash_Lookup_row10 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row10Struct>getLookup(matchingModeEnum_row10);

				globalMap.put("tHash_Lookup_row10", tHash_Lookup_row10);

				/**
				 * [tAdvancedHash_row10 begin ] stop
				 */

				/**
				 * [tDBInput_16 begin ] start
				 */

				ok_Hash.put("tDBInput_16", false);
				start_Hash.put("tDBInput_16", System.currentTimeMillis());

				currentComponent = "tDBInput_16";

				int tos_count_tDBInput_16 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_16 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_16 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_16 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_16, talendToDBArray_tDBInput_16);
				int nb_line_tDBInput_16 = 0;
				java.sql.Connection conn_tDBInput_16 = null;
				String driverClass_tDBInput_16 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_16 = java.lang.Class.forName(driverClass_tDBInput_16);
				String dbUser_tDBInput_16 = "sa";

				final String decryptedPassword_tDBInput_16 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:OZ6oCanCuN6Bu27APhhb5P0A2mqfDKQr8gzoUUAAB5UjHbnEaQo=");

				String dbPwd_tDBInput_16 = decryptedPassword_tDBInput_16;

				String port_tDBInput_16 = "1433";
				String dbname_tDBInput_16 = "orange_DW__";
				String url_tDBInput_16 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_16)) {
					url_tDBInput_16 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_16)) {
					url_tDBInput_16 += "//" + "orange_DW__";
				}
				url_tDBInput_16 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_16 = "dbo";

				conn_tDBInput_16 = java.sql.DriverManager.getConnection(url_tDBInput_16, dbUser_tDBInput_16,
						dbPwd_tDBInput_16);

				java.sql.Statement stmt_tDBInput_16 = conn_tDBInput_16.createStatement();

				String dbquery_tDBInput_16 = "SELECT dbo.Dim_equipe.equipe_id,\n		dbo.Dim_equipe.Equipe\nFROM	dbo.Dim_equipe";

				globalMap.put("tDBInput_16_QUERY", dbquery_tDBInput_16);
				java.sql.ResultSet rs_tDBInput_16 = null;

				try {
					rs_tDBInput_16 = stmt_tDBInput_16.executeQuery(dbquery_tDBInput_16);
					java.sql.ResultSetMetaData rsmd_tDBInput_16 = rs_tDBInput_16.getMetaData();
					int colQtyInRs_tDBInput_16 = rsmd_tDBInput_16.getColumnCount();

					String tmpContent_tDBInput_16 = null;

					while (rs_tDBInput_16.next()) {
						nb_line_tDBInput_16++;

						if (colQtyInRs_tDBInput_16 < 1) {
							row10.equipe_id = 0;
						} else {

							row10.equipe_id = rs_tDBInput_16.getInt(1);
							if (rs_tDBInput_16.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_16 < 2) {
							row10.Equipe = null;
						} else {

							tmpContent_tDBInput_16 = rs_tDBInput_16.getString(2);
							if (tmpContent_tDBInput_16 != null) {
								if (talendToDBList_tDBInput_16.contains(
										rsmd_tDBInput_16.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row10.Equipe = FormatterUtils.formatUnwithE(tmpContent_tDBInput_16);
								} else {
									row10.Equipe = tmpContent_tDBInput_16;
								}
							} else {
								row10.Equipe = null;
							}
						}

						/**
						 * [tDBInput_16 begin ] stop
						 */

						/**
						 * [tDBInput_16 main ] start
						 */

						currentComponent = "tDBInput_16";

						tos_count_tDBInput_16++;

						/**
						 * [tDBInput_16 main ] stop
						 */

						/**
						 * [tDBInput_16 process_data_begin ] start
						 */

						currentComponent = "tDBInput_16";

						/**
						 * [tDBInput_16 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row10 main ] start
						 */

						currentComponent = "tAdvancedHash_row10";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row10"

							);
						}

						row10Struct row10_HashRow = new row10Struct();

						row10_HashRow.equipe_id = row10.equipe_id;

						row10_HashRow.Equipe = row10.Equipe;

						tHash_Lookup_row10.put(row10_HashRow);

						tos_count_tAdvancedHash_row10++;

						/**
						 * [tAdvancedHash_row10 main ] stop
						 */

						/**
						 * [tAdvancedHash_row10 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row10";

						/**
						 * [tAdvancedHash_row10 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row10 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row10";

						/**
						 * [tAdvancedHash_row10 process_data_end ] stop
						 */

						/**
						 * [tDBInput_16 process_data_end ] start
						 */

						currentComponent = "tDBInput_16";

						/**
						 * [tDBInput_16 process_data_end ] stop
						 */

						/**
						 * [tDBInput_16 end ] start
						 */

						currentComponent = "tDBInput_16";

					}
				} finally {
					if (rs_tDBInput_16 != null) {
						rs_tDBInput_16.close();
					}
					if (stmt_tDBInput_16 != null) {
						stmt_tDBInput_16.close();
					}
					if (conn_tDBInput_16 != null && !conn_tDBInput_16.isClosed()) {

						conn_tDBInput_16.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_16_NB_LINE", nb_line_tDBInput_16);

				ok_Hash.put("tDBInput_16", true);
				end_Hash.put("tDBInput_16", System.currentTimeMillis());

				/**
				 * [tDBInput_16 end ] stop
				 */

				/**
				 * [tAdvancedHash_row10 end ] start
				 */

				currentComponent = "tAdvancedHash_row10";

				tHash_Lookup_row10.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row10");
				}

				ok_Hash.put("tAdvancedHash_row10", true);
				end_Hash.put("tAdvancedHash_row10", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row10 end ] stop
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
				 * [tDBInput_16 finally ] start
				 */

				currentComponent = "tDBInput_16";

				/**
				 * [tDBInput_16 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row10 finally ] start
				 */

				currentComponent = "tAdvancedHash_row10";

				/**
				 * [tAdvancedHash_row10 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_16_SUBPROCESS_STATE", 1);
	}

	public static class row9Struct implements routines.system.IPersistableComparableLookupRow<row9Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Extension_id;

		public int getExtension_id() {
			return this.Extension_id;
		}

		public String Extension__Opportunite;

		public String getExtension__Opportunite() {
			return this.Extension__Opportunite;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result
						+ ((this.Extension__Opportunite == null) ? 0 : this.Extension__Opportunite.hashCode());

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

			if (this.Extension__Opportunite == null) {
				if (other.Extension__Opportunite != null)
					return false;

			} else if (!this.Extension__Opportunite.equals(other.Extension__Opportunite))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other.Extension_id = this.Extension_id;
			other.Extension__Opportunite = this.Extension__Opportunite;

		}

		public void copyKeysDataTo(row9Struct other) {

			other.Extension__Opportunite = this.Extension__Opportunite;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Extension__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Extension__Opportunite, dos);

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

				this.Extension_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Extension_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.Extension_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.Extension_id);

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
			sb.append("Extension_id=" + String.valueOf(Extension_id));
			sb.append(",Extension__Opportunite=" + Extension__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Extension__Opportunite, other.Extension__Opportunite);
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
				// source node:tDBInput_17 - inputs:(after_tFileInputExcel_1)
				// outputs:(row9,row9) | target node:tAdvancedHash_row9 - inputs:(row9)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row9 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row9Struct>getLookup(matchingModeEnum_row9);

				globalMap.put("tHash_Lookup_row9", tHash_Lookup_row9);

				/**
				 * [tAdvancedHash_row9 begin ] stop
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
						"enc:routine.encryption.key.v1:Q8OPrzI1/Y/57VoegudsITyb7oIUp4AHq/ke4MzBsBQcUQDyhOg=");

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

				String dbquery_tDBInput_17 = "SELECT dbo.Dim_Extension_mobile.Extension_id,\n		dbo.Dim_Extension_mobile.Extension__Opportunite\nFROM	dbo.Dim_Extension_"
						+ "mobile";

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
							row9.Extension_id = 0;
						} else {

							row9.Extension_id = rs_tDBInput_17.getInt(1);
							if (rs_tDBInput_17.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_17 < 2) {
							row9.Extension__Opportunite = null;
						} else {

							tmpContent_tDBInput_17 = rs_tDBInput_17.getString(2);
							if (tmpContent_tDBInput_17 != null) {
								if (talendToDBList_tDBInput_17.contains(
										rsmd_tDBInput_17.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row9.Extension__Opportunite = FormatterUtils.formatUnwithE(tmpContent_tDBInput_17);
								} else {
									row9.Extension__Opportunite = tmpContent_tDBInput_17;
								}
							} else {
								row9.Extension__Opportunite = null;
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
						 * [tAdvancedHash_row9 main ] start
						 */

						currentComponent = "tAdvancedHash_row9";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row9"

							);
						}

						row9Struct row9_HashRow = new row9Struct();

						row9_HashRow.Extension_id = row9.Extension_id;

						row9_HashRow.Extension__Opportunite = row9.Extension__Opportunite;

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
				 * [tDBInput_17 finally ] start
				 */

				currentComponent = "tDBInput_17";

				/**
				 * [tDBInput_17 finally ] stop
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

		globalMap.put("tDBInput_17_SUBPROCESS_STATE", 1);
	}

	public static class row8Struct implements routines.system.IPersistableComparableLookupRow<row8Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int gouvernorat_id;

		public int getGouvernorat_id() {
			return this.gouvernorat_id;
		}

		public String Gouvernorat;

		public String getGouvernorat() {
			return this.Gouvernorat;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Gouvernorat == null) ? 0 : this.Gouvernorat.hashCode());

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

			if (this.Gouvernorat == null) {
				if (other.Gouvernorat != null)
					return false;

			} else if (!this.Gouvernorat.equals(other.Gouvernorat))

				return false;

			return true;
		}

		public void copyDataTo(row8Struct other) {

			other.gouvernorat_id = this.gouvernorat_id;
			other.Gouvernorat = this.Gouvernorat;

		}

		public void copyKeysDataTo(row8Struct other) {

			other.Gouvernorat = this.Gouvernorat;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Gouvernorat = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Gouvernorat = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Gouvernorat, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Gouvernorat, dos);

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

				this.gouvernorat_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.gouvernorat_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.gouvernorat_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.gouvernorat_id);

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
			sb.append("gouvernorat_id=" + String.valueOf(gouvernorat_id));
			sb.append(",Gouvernorat=" + Gouvernorat);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Gouvernorat, other.Gouvernorat);
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
				// source node:tDBInput_18 - inputs:(after_tFileInputExcel_1)
				// outputs:(row8,row8) | target node:tAdvancedHash_row8 - inputs:(row8)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row8 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row8Struct>getLookup(matchingModeEnum_row8);

				globalMap.put("tHash_Lookup_row8", tHash_Lookup_row8);

				/**
				 * [tAdvancedHash_row8 begin ] stop
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
						"enc:routine.encryption.key.v1:KlhkdXbTm5Mi2wVkYeRzqfLEm9v+C0AVa0GiO/03wzst80oB608=");

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

				String dbquery_tDBInput_18 = "SELECT dbo.Dim_gouvernorat.gouvernorat_id,\n		dbo.Dim_gouvernorat.Gouvernorat\nFROM	dbo.Dim_gouvernorat";

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
							row8.gouvernorat_id = 0;
						} else {

							row8.gouvernorat_id = rs_tDBInput_18.getInt(1);
							if (rs_tDBInput_18.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_18 < 2) {
							row8.Gouvernorat = null;
						} else {

							tmpContent_tDBInput_18 = rs_tDBInput_18.getString(2);
							if (tmpContent_tDBInput_18 != null) {
								if (talendToDBList_tDBInput_18.contains(
										rsmd_tDBInput_18.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row8.Gouvernorat = FormatterUtils.formatUnwithE(tmpContent_tDBInput_18);
								} else {
									row8.Gouvernorat = tmpContent_tDBInput_18;
								}
							} else {
								row8.Gouvernorat = null;
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
						 * [tAdvancedHash_row8 main ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row8"

							);
						}

						row8Struct row8_HashRow = new row8Struct();

						row8_HashRow.gouvernorat_id = row8.gouvernorat_id;

						row8_HashRow.Gouvernorat = row8.Gouvernorat;

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
				 * [tDBInput_18 finally ] start
				 */

				currentComponent = "tDBInput_18";

				/**
				 * [tDBInput_18 finally ] stop
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

		globalMap.put("tDBInput_18_SUBPROCESS_STATE", 1);
	}

	public static class row7Struct implements routines.system.IPersistableComparableLookupRow<row7Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int nature_offre_id;

		public int getNature_offre_id() {
			return this.nature_offre_id;
		}

		public String Nature_Offre;

		public String getNature_Offre() {
			return this.Nature_Offre;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Nature_Offre == null) ? 0 : this.Nature_Offre.hashCode());

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

			if (this.Nature_Offre == null) {
				if (other.Nature_Offre != null)
					return false;

			} else if (!this.Nature_Offre.equals(other.Nature_Offre))

				return false;

			return true;
		}

		public void copyDataTo(row7Struct other) {

			other.nature_offre_id = this.nature_offre_id;
			other.Nature_Offre = this.Nature_Offre;

		}

		public void copyKeysDataTo(row7Struct other) {

			other.Nature_Offre = this.Nature_Offre;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Nature_Offre = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Nature_Offre, dos);

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

				this.nature_offre_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.nature_offre_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.nature_offre_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.nature_offre_id);

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
			sb.append("nature_offre_id=" + String.valueOf(nature_offre_id));
			sb.append(",Nature_Offre=" + Nature_Offre);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Nature_Offre, other.Nature_Offre);
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
				// source node:tDBInput_19 - inputs:(after_tFileInputExcel_1)
				// outputs:(row7,row7) | target node:tAdvancedHash_row7 - inputs:(row7)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row7 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row7Struct> tHash_Lookup_row7 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row7Struct>getLookup(matchingModeEnum_row7);

				globalMap.put("tHash_Lookup_row7", tHash_Lookup_row7);

				/**
				 * [tAdvancedHash_row7 begin ] stop
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
						"enc:routine.encryption.key.v1:xUShamePGmLsN4YdttBnD2dsg0xtSzOX2fKTeEy/Kwuse8+k6dA=");

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

				String dbquery_tDBInput_19 = "SELECT dbo.Dim_nature_offre.nature_offre_id,\n		dbo.Dim_nature_offre.Nature_Offre\nFROM	dbo.Dim_nature_offre";

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
							row7.nature_offre_id = 0;
						} else {

							row7.nature_offre_id = rs_tDBInput_19.getInt(1);
							if (rs_tDBInput_19.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_19 < 2) {
							row7.Nature_Offre = null;
						} else {

							tmpContent_tDBInput_19 = rs_tDBInput_19.getString(2);
							if (tmpContent_tDBInput_19 != null) {
								if (talendToDBList_tDBInput_19.contains(
										rsmd_tDBInput_19.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row7.Nature_Offre = FormatterUtils.formatUnwithE(tmpContent_tDBInput_19);
								} else {
									row7.Nature_Offre = tmpContent_tDBInput_19;
								}
							} else {
								row7.Nature_Offre = null;
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
						 * [tAdvancedHash_row7 main ] start
						 */

						currentComponent = "tAdvancedHash_row7";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row7"

							);
						}

						row7Struct row7_HashRow = new row7Struct();

						row7_HashRow.nature_offre_id = row7.nature_offre_id;

						row7_HashRow.Nature_Offre = row7.Nature_Offre;

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
				 * [tDBInput_19 finally ] start
				 */

				currentComponent = "tDBInput_19";

				/**
				 * [tDBInput_19 finally ] stop
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

		globalMap.put("tDBInput_19_SUBPROCESS_STATE", 1);
	}

	public static class row5Struct implements routines.system.IPersistableComparableLookupRow<row5Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
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
			final row5Struct other = (row5Struct) obj;

			if (this.Phase_de_l_opportunite == null) {
				if (other.Phase_de_l_opportunite != null)
					return false;

			} else if (!this.Phase_de_l_opportunite.equals(other.Phase_de_l_opportunite))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.phase_de_l_opportunite_id = this.phase_de_l_opportunite_id;
			other.Phase_de_l_opportunite = this.Phase_de_l_opportunite;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.Phase_de_l_opportunite = this.Phase_de_l_opportunite;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Phase_de_l_opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

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
		public int compareTo(row5Struct other) {

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

	public void tDBInput_21Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_21_SUBPROCESS_STATE", 0);

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
				// source node:tDBInput_21 - inputs:(after_tFileInputExcel_1)
				// outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row5Struct>getLookup(matchingModeEnum_row5);

				globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);

				/**
				 * [tAdvancedHash_row5 begin ] stop
				 */

				/**
				 * [tDBInput_21 begin ] start
				 */

				ok_Hash.put("tDBInput_21", false);
				start_Hash.put("tDBInput_21", System.currentTimeMillis());

				currentComponent = "tDBInput_21";

				int tos_count_tDBInput_21 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_21 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_21 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_21 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_21, talendToDBArray_tDBInput_21);
				int nb_line_tDBInput_21 = 0;
				java.sql.Connection conn_tDBInput_21 = null;
				String driverClass_tDBInput_21 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_21 = java.lang.Class.forName(driverClass_tDBInput_21);
				String dbUser_tDBInput_21 = "sa";

				final String decryptedPassword_tDBInput_21 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:w0qmmVr62UP94B8St1Vm6vWndsZDZoFPjEy0e8iVES8yJeh2HcU=");

				String dbPwd_tDBInput_21 = decryptedPassword_tDBInput_21;

				String port_tDBInput_21 = "1433";
				String dbname_tDBInput_21 = "orange_DW__";
				String url_tDBInput_21 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_21)) {
					url_tDBInput_21 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_21)) {
					url_tDBInput_21 += "//" + "orange_DW__";
				}
				url_tDBInput_21 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_21 = "dbo";

				conn_tDBInput_21 = java.sql.DriverManager.getConnection(url_tDBInput_21, dbUser_tDBInput_21,
						dbPwd_tDBInput_21);

				java.sql.Statement stmt_tDBInput_21 = conn_tDBInput_21.createStatement();

				String dbquery_tDBInput_21 = "SELECT dbo.Dim_phase_de_l_opportunite.\"phase_de_l_opportunité_id\",\n		dbo.Dim_phase_de_l_opportunite.Phase_de_l_opport"
						+ "unite\nFROM	dbo.Dim_phase_de_l_opportunite";

				globalMap.put("tDBInput_21_QUERY", dbquery_tDBInput_21);
				java.sql.ResultSet rs_tDBInput_21 = null;

				try {
					rs_tDBInput_21 = stmt_tDBInput_21.executeQuery(dbquery_tDBInput_21);
					java.sql.ResultSetMetaData rsmd_tDBInput_21 = rs_tDBInput_21.getMetaData();
					int colQtyInRs_tDBInput_21 = rsmd_tDBInput_21.getColumnCount();

					String tmpContent_tDBInput_21 = null;

					while (rs_tDBInput_21.next()) {
						nb_line_tDBInput_21++;

						if (colQtyInRs_tDBInput_21 < 1) {
							row5.phase_de_l_opportunite_id = 0;
						} else {

							row5.phase_de_l_opportunite_id = rs_tDBInput_21.getInt(1);
							if (rs_tDBInput_21.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_21 < 2) {
							row5.Phase_de_l_opportunite = null;
						} else {

							tmpContent_tDBInput_21 = rs_tDBInput_21.getString(2);
							if (tmpContent_tDBInput_21 != null) {
								if (talendToDBList_tDBInput_21.contains(
										rsmd_tDBInput_21.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row5.Phase_de_l_opportunite = FormatterUtils.formatUnwithE(tmpContent_tDBInput_21);
								} else {
									row5.Phase_de_l_opportunite = tmpContent_tDBInput_21;
								}
							} else {
								row5.Phase_de_l_opportunite = null;
							}
						}

						/**
						 * [tDBInput_21 begin ] stop
						 */

						/**
						 * [tDBInput_21 main ] start
						 */

						currentComponent = "tDBInput_21";

						tos_count_tDBInput_21++;

						/**
						 * [tDBInput_21 main ] stop
						 */

						/**
						 * [tDBInput_21 process_data_begin ] start
						 */

						currentComponent = "tDBInput_21";

						/**
						 * [tDBInput_21 process_data_begin ] stop
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

						row5_HashRow.phase_de_l_opportunite_id = row5.phase_de_l_opportunite_id;

						row5_HashRow.Phase_de_l_opportunite = row5.Phase_de_l_opportunite;

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
						 * [tDBInput_21 process_data_end ] start
						 */

						currentComponent = "tDBInput_21";

						/**
						 * [tDBInput_21 process_data_end ] stop
						 */

						/**
						 * [tDBInput_21 end ] start
						 */

						currentComponent = "tDBInput_21";

					}
				} finally {
					if (rs_tDBInput_21 != null) {
						rs_tDBInput_21.close();
					}
					if (stmt_tDBInput_21 != null) {
						stmt_tDBInput_21.close();
					}
					if (conn_tDBInput_21 != null && !conn_tDBInput_21.isClosed()) {

						conn_tDBInput_21.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_21_NB_LINE", nb_line_tDBInput_21);

				ok_Hash.put("tDBInput_21", true);
				end_Hash.put("tDBInput_21", System.currentTimeMillis());

				/**
				 * [tDBInput_21 end ] stop
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
				 * [tDBInput_21 finally ] start
				 */

				currentComponent = "tDBInput_21";

				/**
				 * [tDBInput_21 finally ] stop
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

		globalMap.put("tDBInput_21_SUBPROCESS_STATE", 1);
	}

	public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
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
			final row4Struct other = (row4Struct) obj;

			if (this.Client_Prospect == null) {
				if (other.Client_Prospect != null)
					return false;

			} else if (!this.Client_Prospect.equals(other.Client_Prospect))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.Client_Prospect_id = this.Client_Prospect_id;
			other.Client_Prospect = this.Client_Prospect;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.Client_Prospect = this.Client_Prospect;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

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
		public int compareTo(row4Struct other) {

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

	public void tDBInput_22Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_22_SUBPROCESS_STATE", 0);

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
				// source node:tDBInput_22 - inputs:(after_tFileInputExcel_1)
				// outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row4Struct>getLookup(matchingModeEnum_row4);

				globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);

				/**
				 * [tAdvancedHash_row4 begin ] stop
				 */

				/**
				 * [tDBInput_22 begin ] start
				 */

				ok_Hash.put("tDBInput_22", false);
				start_Hash.put("tDBInput_22", System.currentTimeMillis());

				currentComponent = "tDBInput_22";

				int tos_count_tDBInput_22 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_22 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_22 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_22 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_22, talendToDBArray_tDBInput_22);
				int nb_line_tDBInput_22 = 0;
				java.sql.Connection conn_tDBInput_22 = null;
				String driverClass_tDBInput_22 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_22 = java.lang.Class.forName(driverClass_tDBInput_22);
				String dbUser_tDBInput_22 = "sa";

				final String decryptedPassword_tDBInput_22 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:P3YteCwYIVTNaA8EezjiYhLbdBkOrCl0nbR2GfIhi0kvMXEDVXs=");

				String dbPwd_tDBInput_22 = decryptedPassword_tDBInput_22;

				String port_tDBInput_22 = "1433";
				String dbname_tDBInput_22 = "orange_DW__";
				String url_tDBInput_22 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_22)) {
					url_tDBInput_22 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_22)) {
					url_tDBInput_22 += "//" + "orange_DW__";
				}
				url_tDBInput_22 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_22 = "dbo";

				conn_tDBInput_22 = java.sql.DriverManager.getConnection(url_tDBInput_22, dbUser_tDBInput_22,
						dbPwd_tDBInput_22);

				java.sql.Statement stmt_tDBInput_22 = conn_tDBInput_22.createStatement();

				String dbquery_tDBInput_22 = "SELECT dbo.Dim_Raison_sociale.Client_Prospect_id,\n		dbo.Dim_Raison_sociale.Client_Prospect\nFROM	dbo.Dim_Raison_sociale";

				globalMap.put("tDBInput_22_QUERY", dbquery_tDBInput_22);
				java.sql.ResultSet rs_tDBInput_22 = null;

				try {
					rs_tDBInput_22 = stmt_tDBInput_22.executeQuery(dbquery_tDBInput_22);
					java.sql.ResultSetMetaData rsmd_tDBInput_22 = rs_tDBInput_22.getMetaData();
					int colQtyInRs_tDBInput_22 = rsmd_tDBInput_22.getColumnCount();

					String tmpContent_tDBInput_22 = null;

					while (rs_tDBInput_22.next()) {
						nb_line_tDBInput_22++;

						if (colQtyInRs_tDBInput_22 < 1) {
							row4.Client_Prospect_id = 0;
						} else {

							row4.Client_Prospect_id = rs_tDBInput_22.getInt(1);
							if (rs_tDBInput_22.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_22 < 2) {
							row4.Client_Prospect = null;
						} else {

							tmpContent_tDBInput_22 = rs_tDBInput_22.getString(2);
							if (tmpContent_tDBInput_22 != null) {
								if (talendToDBList_tDBInput_22.contains(
										rsmd_tDBInput_22.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row4.Client_Prospect = FormatterUtils.formatUnwithE(tmpContent_tDBInput_22);
								} else {
									row4.Client_Prospect = tmpContent_tDBInput_22;
								}
							} else {
								row4.Client_Prospect = null;
							}
						}

						/**
						 * [tDBInput_22 begin ] stop
						 */

						/**
						 * [tDBInput_22 main ] start
						 */

						currentComponent = "tDBInput_22";

						tos_count_tDBInput_22++;

						/**
						 * [tDBInput_22 main ] stop
						 */

						/**
						 * [tDBInput_22 process_data_begin ] start
						 */

						currentComponent = "tDBInput_22";

						/**
						 * [tDBInput_22 process_data_begin ] stop
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

						row4_HashRow.Client_Prospect_id = row4.Client_Prospect_id;

						row4_HashRow.Client_Prospect = row4.Client_Prospect;

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
						 * [tDBInput_22 process_data_end ] start
						 */

						currentComponent = "tDBInput_22";

						/**
						 * [tDBInput_22 process_data_end ] stop
						 */

						/**
						 * [tDBInput_22 end ] start
						 */

						currentComponent = "tDBInput_22";

					}
				} finally {
					if (rs_tDBInput_22 != null) {
						rs_tDBInput_22.close();
					}
					if (stmt_tDBInput_22 != null) {
						stmt_tDBInput_22.close();
					}
					if (conn_tDBInput_22 != null && !conn_tDBInput_22.isClosed()) {

						conn_tDBInput_22.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_22_NB_LINE", nb_line_tDBInput_22);

				ok_Hash.put("tDBInput_22", true);
				end_Hash.put("tDBInput_22", System.currentTimeMillis());

				/**
				 * [tDBInput_22 end ] stop
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
				 * [tDBInput_22 finally ] start
				 */

				currentComponent = "tDBInput_22";

				/**
				 * [tDBInput_22 finally ] stop
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

		globalMap.put("tDBInput_22_SUBPROCESS_STATE", 1);
	}

	public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
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
			final row3Struct other = (row3Struct) obj;

			if (this.Staut_Final == null) {
				if (other.Staut_Final != null)
					return false;

			} else if (!this.Staut_Final.equals(other.Staut_Final))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.statu_final_id = this.statu_final_id;
			other.Staut_Final = this.Staut_Final;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.Staut_Final = this.Staut_Final;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Staut_Final = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

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
		public int compareTo(row3Struct other) {

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

	public void tDBInput_23Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_23_SUBPROCESS_STATE", 0);

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
				// source node:tDBInput_23 - inputs:(after_tFileInputExcel_1)
				// outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row3Struct>getLookup(matchingModeEnum_row3);

				globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);

				/**
				 * [tAdvancedHash_row3 begin ] stop
				 */

				/**
				 * [tDBInput_23 begin ] start
				 */

				ok_Hash.put("tDBInput_23", false);
				start_Hash.put("tDBInput_23", System.currentTimeMillis());

				currentComponent = "tDBInput_23";

				int tos_count_tDBInput_23 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_23 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_23 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_23 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_23, talendToDBArray_tDBInput_23);
				int nb_line_tDBInput_23 = 0;
				java.sql.Connection conn_tDBInput_23 = null;
				String driverClass_tDBInput_23 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_23 = java.lang.Class.forName(driverClass_tDBInput_23);
				String dbUser_tDBInput_23 = "sa";

				final String decryptedPassword_tDBInput_23 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:JzfF4+WM+OGbhh5flVVCLSQxbGEQ6IDjkswfEz0h8oDKyZJ+D2E=");

				String dbPwd_tDBInput_23 = decryptedPassword_tDBInput_23;

				String port_tDBInput_23 = "1433";
				String dbname_tDBInput_23 = "orange_DW__";
				String url_tDBInput_23 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_23)) {
					url_tDBInput_23 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_23)) {
					url_tDBInput_23 += "//" + "orange_DW__";
				}
				url_tDBInput_23 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_23 = "dbo";

				conn_tDBInput_23 = java.sql.DriverManager.getConnection(url_tDBInput_23, dbUser_tDBInput_23,
						dbPwd_tDBInput_23);

				java.sql.Statement stmt_tDBInput_23 = conn_tDBInput_23.createStatement();

				String dbquery_tDBInput_23 = "SELECT dbo.Dim_statu_final.statu_final_id,\n		dbo.Dim_statu_final.Staut_Final\nFROM	dbo.Dim_statu_final";

				globalMap.put("tDBInput_23_QUERY", dbquery_tDBInput_23);
				java.sql.ResultSet rs_tDBInput_23 = null;

				try {
					rs_tDBInput_23 = stmt_tDBInput_23.executeQuery(dbquery_tDBInput_23);
					java.sql.ResultSetMetaData rsmd_tDBInput_23 = rs_tDBInput_23.getMetaData();
					int colQtyInRs_tDBInput_23 = rsmd_tDBInput_23.getColumnCount();

					String tmpContent_tDBInput_23 = null;

					while (rs_tDBInput_23.next()) {
						nb_line_tDBInput_23++;

						if (colQtyInRs_tDBInput_23 < 1) {
							row3.statu_final_id = 0;
						} else {

							row3.statu_final_id = rs_tDBInput_23.getInt(1);
							if (rs_tDBInput_23.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_23 < 2) {
							row3.Staut_Final = null;
						} else {

							tmpContent_tDBInput_23 = rs_tDBInput_23.getString(2);
							if (tmpContent_tDBInput_23 != null) {
								if (talendToDBList_tDBInput_23.contains(
										rsmd_tDBInput_23.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row3.Staut_Final = FormatterUtils.formatUnwithE(tmpContent_tDBInput_23);
								} else {
									row3.Staut_Final = tmpContent_tDBInput_23;
								}
							} else {
								row3.Staut_Final = null;
							}
						}

						/**
						 * [tDBInput_23 begin ] stop
						 */

						/**
						 * [tDBInput_23 main ] start
						 */

						currentComponent = "tDBInput_23";

						tos_count_tDBInput_23++;

						/**
						 * [tDBInput_23 main ] stop
						 */

						/**
						 * [tDBInput_23 process_data_begin ] start
						 */

						currentComponent = "tDBInput_23";

						/**
						 * [tDBInput_23 process_data_begin ] stop
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

						row3_HashRow.statu_final_id = row3.statu_final_id;

						row3_HashRow.Staut_Final = row3.Staut_Final;

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
						 * [tDBInput_23 process_data_end ] start
						 */

						currentComponent = "tDBInput_23";

						/**
						 * [tDBInput_23 process_data_end ] stop
						 */

						/**
						 * [tDBInput_23 end ] start
						 */

						currentComponent = "tDBInput_23";

					}
				} finally {
					if (rs_tDBInput_23 != null) {
						rs_tDBInput_23.close();
					}
					if (stmt_tDBInput_23 != null) {
						stmt_tDBInput_23.close();
					}
					if (conn_tDBInput_23 != null && !conn_tDBInput_23.isClosed()) {

						conn_tDBInput_23.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_23_NB_LINE", nb_line_tDBInput_23);

				ok_Hash.put("tDBInput_23", true);
				end_Hash.put("tDBInput_23", System.currentTimeMillis());

				/**
				 * [tDBInput_23 end ] stop
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
				 * [tDBInput_23 finally ] start
				 */

				currentComponent = "tDBInput_23";

				/**
				 * [tDBInput_23 finally ] stop
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

		globalMap.put("tDBInput_23_SUBPROCESS_STATE", 1);
	}

	public static class row21Struct implements routines.system.IPersistableComparableLookupRow<row21Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
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
			final row21Struct other = (row21Struct) obj;

			if (this.Account_Manager__Client_Prospect == null) {
				if (other.Account_Manager__Client_Prospect != null)
					return false;

			} else if (!this.Account_Manager__Client_Prospect.equals(other.Account_Manager__Client_Prospect))

				return false;

			return true;
		}

		public void copyDataTo(row21Struct other) {

			other.membre_equipe_id = this.membre_equipe_id;
			other.Account_Manager__Client_Prospect = this.Account_Manager__Client_Prospect;

		}

		public void copyKeysDataTo(row21Struct other) {

			other.Account_Manager__Client_Prospect = this.Account_Manager__Client_Prospect;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Account_Manager__Client_Prospect = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

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
		public int compareTo(row21Struct other) {

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

	public void tDBInput_25Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_25_SUBPROCESS_STATE", 0);

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

				row21Struct row21 = new row21Struct();

				/**
				 * [tAdvancedHash_row21 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row21", false);
				start_Hash.put("tAdvancedHash_row21", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row21";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row21");
				}

				int tos_count_tAdvancedHash_row21 = 0;

				// connection name:row21
				// source node:tDBInput_25 - inputs:(after_tFileInputExcel_1)
				// outputs:(row21,row21) | target node:tAdvancedHash_row21 - inputs:(row21)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row21 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row21Struct> tHash_Lookup_row21 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row21Struct>getLookup(matchingModeEnum_row21);

				globalMap.put("tHash_Lookup_row21", tHash_Lookup_row21);

				/**
				 * [tAdvancedHash_row21 begin ] stop
				 */

				/**
				 * [tDBInput_25 begin ] start
				 */

				ok_Hash.put("tDBInput_25", false);
				start_Hash.put("tDBInput_25", System.currentTimeMillis());

				currentComponent = "tDBInput_25";

				int tos_count_tDBInput_25 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_25 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_25 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_25 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_25, talendToDBArray_tDBInput_25);
				int nb_line_tDBInput_25 = 0;
				java.sql.Connection conn_tDBInput_25 = null;
				String driverClass_tDBInput_25 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_25 = java.lang.Class.forName(driverClass_tDBInput_25);
				String dbUser_tDBInput_25 = "sa";

				final String decryptedPassword_tDBInput_25 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:XzFAk6CkQLblzNlS6mI7YLVtOpBQ6Jd92iIqsVnmqkzrDeWgOls=");

				String dbPwd_tDBInput_25 = decryptedPassword_tDBInput_25;

				String port_tDBInput_25 = "1433";
				String dbname_tDBInput_25 = "orange_DW__";
				String url_tDBInput_25 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_25)) {
					url_tDBInput_25 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_25)) {
					url_tDBInput_25 += "//" + "orange_DW__";
				}
				url_tDBInput_25 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_25 = "dbo";

				conn_tDBInput_25 = java.sql.DriverManager.getConnection(url_tDBInput_25, dbUser_tDBInput_25,
						dbPwd_tDBInput_25);

				java.sql.Statement stmt_tDBInput_25 = conn_tDBInput_25.createStatement();

				String dbquery_tDBInput_25 = "SELECT dbo.Dim_membre.\"membre_équipe_id\",\n		dbo.Dim_membre.Account_Manager__Client_Prospect\nFROM	dbo.Dim_membre";

				globalMap.put("tDBInput_25_QUERY", dbquery_tDBInput_25);
				java.sql.ResultSet rs_tDBInput_25 = null;

				try {
					rs_tDBInput_25 = stmt_tDBInput_25.executeQuery(dbquery_tDBInput_25);
					java.sql.ResultSetMetaData rsmd_tDBInput_25 = rs_tDBInput_25.getMetaData();
					int colQtyInRs_tDBInput_25 = rsmd_tDBInput_25.getColumnCount();

					String tmpContent_tDBInput_25 = null;

					while (rs_tDBInput_25.next()) {
						nb_line_tDBInput_25++;

						if (colQtyInRs_tDBInput_25 < 1) {
							row21.membre_equipe_id = 0;
						} else {

							row21.membre_equipe_id = rs_tDBInput_25.getInt(1);
							if (rs_tDBInput_25.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_25 < 2) {
							row21.Account_Manager__Client_Prospect = null;
						} else {

							tmpContent_tDBInput_25 = rs_tDBInput_25.getString(2);
							if (tmpContent_tDBInput_25 != null) {
								if (talendToDBList_tDBInput_25.contains(
										rsmd_tDBInput_25.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row21.Account_Manager__Client_Prospect = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_25);
								} else {
									row21.Account_Manager__Client_Prospect = tmpContent_tDBInput_25;
								}
							} else {
								row21.Account_Manager__Client_Prospect = null;
							}
						}

						/**
						 * [tDBInput_25 begin ] stop
						 */

						/**
						 * [tDBInput_25 main ] start
						 */

						currentComponent = "tDBInput_25";

						tos_count_tDBInput_25++;

						/**
						 * [tDBInput_25 main ] stop
						 */

						/**
						 * [tDBInput_25 process_data_begin ] start
						 */

						currentComponent = "tDBInput_25";

						/**
						 * [tDBInput_25 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row21 main ] start
						 */

						currentComponent = "tAdvancedHash_row21";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row21"

							);
						}

						row21Struct row21_HashRow = new row21Struct();

						row21_HashRow.membre_equipe_id = row21.membre_equipe_id;

						row21_HashRow.Account_Manager__Client_Prospect = row21.Account_Manager__Client_Prospect;

						tHash_Lookup_row21.put(row21_HashRow);

						tos_count_tAdvancedHash_row21++;

						/**
						 * [tAdvancedHash_row21 main ] stop
						 */

						/**
						 * [tAdvancedHash_row21 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row21";

						/**
						 * [tAdvancedHash_row21 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row21 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row21";

						/**
						 * [tAdvancedHash_row21 process_data_end ] stop
						 */

						/**
						 * [tDBInput_25 process_data_end ] start
						 */

						currentComponent = "tDBInput_25";

						/**
						 * [tDBInput_25 process_data_end ] stop
						 */

						/**
						 * [tDBInput_25 end ] start
						 */

						currentComponent = "tDBInput_25";

					}
				} finally {
					if (rs_tDBInput_25 != null) {
						rs_tDBInput_25.close();
					}
					if (stmt_tDBInput_25 != null) {
						stmt_tDBInput_25.close();
					}
					if (conn_tDBInput_25 != null && !conn_tDBInput_25.isClosed()) {

						conn_tDBInput_25.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_25_NB_LINE", nb_line_tDBInput_25);

				ok_Hash.put("tDBInput_25", true);
				end_Hash.put("tDBInput_25", System.currentTimeMillis());

				/**
				 * [tDBInput_25 end ] stop
				 */

				/**
				 * [tAdvancedHash_row21 end ] start
				 */

				currentComponent = "tAdvancedHash_row21";

				tHash_Lookup_row21.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row21");
				}

				ok_Hash.put("tAdvancedHash_row21", true);
				end_Hash.put("tAdvancedHash_row21", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row21 end ] stop
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
				 * [tDBInput_25 finally ] start
				 */

				currentComponent = "tDBInput_25";

				/**
				 * [tDBInput_25 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row21 finally ] start
				 */

				currentComponent = "tAdvancedHash_row21";

				/**
				 * [tAdvancedHash_row21 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_25_SUBPROCESS_STATE", 1);
	}

	public static class row15Struct implements routines.system.IPersistableComparableLookupRow<row15Struct> {
		final static byte[] commonByteArrayLock_ORANGE_Base_mobile_orange = new byte[0];
		static byte[] commonByteArray_ORANGE_Base_mobile_orange = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Dim_Reference_OSM_Opportunite_id;

		public int getDim_Reference_OSM_Opportunite_id() {
			return this.Dim_Reference_OSM_Opportunite_id;
		}

		public String Reference_OSM__Opportunite;

		public String getReference_OSM__Opportunite() {
			return this.Reference_OSM__Opportunite;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result
						+ ((this.Reference_OSM__Opportunite == null) ? 0 : this.Reference_OSM__Opportunite.hashCode());

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
			final row15Struct other = (row15Struct) obj;

			if (this.Reference_OSM__Opportunite == null) {
				if (other.Reference_OSM__Opportunite != null)
					return false;

			} else if (!this.Reference_OSM__Opportunite.equals(other.Reference_OSM__Opportunite))

				return false;

			return true;
		}

		public void copyDataTo(row15Struct other) {

			other.Dim_Reference_OSM_Opportunite_id = this.Dim_Reference_OSM_Opportunite_id;
			other.Reference_OSM__Opportunite = this.Reference_OSM__Opportunite;

		}

		public void copyKeysDataTo(row15Struct other) {

			other.Reference_OSM__Opportunite = this.Reference_OSM__Opportunite;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_Base_mobile_orange.length) {
					if (length < 1024 && commonByteArray_ORANGE_Base_mobile_orange.length == 0) {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[1024];
					} else {
						commonByteArray_ORANGE_Base_mobile_orange = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_Base_mobile_orange, 0, length);
				strReturn = new String(commonByteArray_ORANGE_Base_mobile_orange, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_Base_mobile_orange) {

				try {

					int length = 0;

					this.Reference_OSM__Opportunite = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Reference_OSM__Opportunite, dos);

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

				this.Dim_Reference_OSM_Opportunite_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Dim_Reference_OSM_Opportunite_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.Dim_Reference_OSM_Opportunite_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.Dim_Reference_OSM_Opportunite_id);

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
			sb.append("Dim_Reference_OSM_Opportunite_id=" + String.valueOf(Dim_Reference_OSM_Opportunite_id));
			sb.append(",Reference_OSM__Opportunite=" + Reference_OSM__Opportunite);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row15Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Reference_OSM__Opportunite, other.Reference_OSM__Opportunite);
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

	public void tDBInput_26Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_26_SUBPROCESS_STATE", 0);

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

				row15Struct row15 = new row15Struct();

				/**
				 * [tAdvancedHash_row15 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row15", false);
				start_Hash.put("tAdvancedHash_row15", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row15";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row15");
				}

				int tos_count_tAdvancedHash_row15 = 0;

				// connection name:row15
				// source node:tDBInput_26 - inputs:(after_tFileInputExcel_1)
				// outputs:(row15,row15) | target node:tAdvancedHash_row15 - inputs:(row15)
				// outputs:()
				// linked node: tMap_1 -
				// inputs:(correction1,row2,row13,row10,row9,row8,row7,row5,row4,row3,row21,row15)
				// outputs:(orange)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row15 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row15Struct> tHash_Lookup_row15 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row15Struct>getLookup(matchingModeEnum_row15);

				globalMap.put("tHash_Lookup_row15", tHash_Lookup_row15);

				/**
				 * [tAdvancedHash_row15 begin ] stop
				 */

				/**
				 * [tDBInput_26 begin ] start
				 */

				ok_Hash.put("tDBInput_26", false);
				start_Hash.put("tDBInput_26", System.currentTimeMillis());

				currentComponent = "tDBInput_26";

				int tos_count_tDBInput_26 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_26 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_26 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_26 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_26, talendToDBArray_tDBInput_26);
				int nb_line_tDBInput_26 = 0;
				java.sql.Connection conn_tDBInput_26 = null;
				String driverClass_tDBInput_26 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_26 = java.lang.Class.forName(driverClass_tDBInput_26);
				String dbUser_tDBInput_26 = "sa";

				final String decryptedPassword_tDBInput_26 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:/gulhhoVZgX2Y17caelLoOqXBlvbrIJFWicehqUA14BCmG8NMB0=");

				String dbPwd_tDBInput_26 = decryptedPassword_tDBInput_26;

				String port_tDBInput_26 = "1433";
				String dbname_tDBInput_26 = "orange_DW__";
				String url_tDBInput_26 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_26)) {
					url_tDBInput_26 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_26)) {
					url_tDBInput_26 += "//" + "orange_DW__";
				}
				url_tDBInput_26 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_26 = "dbo";

				conn_tDBInput_26 = java.sql.DriverManager.getConnection(url_tDBInput_26, dbUser_tDBInput_26,
						dbPwd_tDBInput_26);

				java.sql.Statement stmt_tDBInput_26 = conn_tDBInput_26.createStatement();

				String dbquery_tDBInput_26 = "SELECT dbo.Dim_Reference_OSM.Dim_Reference_OSM_Opportunite_id,\n		dbo.Dim_Reference_OSM.Reference_OSM__Opportunite\nFROM	"
						+ "dbo.Dim_Reference_OSM";

				globalMap.put("tDBInput_26_QUERY", dbquery_tDBInput_26);
				java.sql.ResultSet rs_tDBInput_26 = null;

				try {
					rs_tDBInput_26 = stmt_tDBInput_26.executeQuery(dbquery_tDBInput_26);
					java.sql.ResultSetMetaData rsmd_tDBInput_26 = rs_tDBInput_26.getMetaData();
					int colQtyInRs_tDBInput_26 = rsmd_tDBInput_26.getColumnCount();

					String tmpContent_tDBInput_26 = null;

					while (rs_tDBInput_26.next()) {
						nb_line_tDBInput_26++;

						if (colQtyInRs_tDBInput_26 < 1) {
							row15.Dim_Reference_OSM_Opportunite_id = 0;
						} else {

							row15.Dim_Reference_OSM_Opportunite_id = rs_tDBInput_26.getInt(1);
							if (rs_tDBInput_26.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_26 < 2) {
							row15.Reference_OSM__Opportunite = null;
						} else {

							tmpContent_tDBInput_26 = rs_tDBInput_26.getString(2);
							if (tmpContent_tDBInput_26 != null) {
								if (talendToDBList_tDBInput_26.contains(
										rsmd_tDBInput_26.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row15.Reference_OSM__Opportunite = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_26);
								} else {
									row15.Reference_OSM__Opportunite = tmpContent_tDBInput_26;
								}
							} else {
								row15.Reference_OSM__Opportunite = null;
							}
						}

						/**
						 * [tDBInput_26 begin ] stop
						 */

						/**
						 * [tDBInput_26 main ] start
						 */

						currentComponent = "tDBInput_26";

						tos_count_tDBInput_26++;

						/**
						 * [tDBInput_26 main ] stop
						 */

						/**
						 * [tDBInput_26 process_data_begin ] start
						 */

						currentComponent = "tDBInput_26";

						/**
						 * [tDBInput_26 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row15 main ] start
						 */

						currentComponent = "tAdvancedHash_row15";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row15"

							);
						}

						row15Struct row15_HashRow = new row15Struct();

						row15_HashRow.Dim_Reference_OSM_Opportunite_id = row15.Dim_Reference_OSM_Opportunite_id;

						row15_HashRow.Reference_OSM__Opportunite = row15.Reference_OSM__Opportunite;

						tHash_Lookup_row15.put(row15_HashRow);

						tos_count_tAdvancedHash_row15++;

						/**
						 * [tAdvancedHash_row15 main ] stop
						 */

						/**
						 * [tAdvancedHash_row15 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row15";

						/**
						 * [tAdvancedHash_row15 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row15 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row15";

						/**
						 * [tAdvancedHash_row15 process_data_end ] stop
						 */

						/**
						 * [tDBInput_26 process_data_end ] start
						 */

						currentComponent = "tDBInput_26";

						/**
						 * [tDBInput_26 process_data_end ] stop
						 */

						/**
						 * [tDBInput_26 end ] start
						 */

						currentComponent = "tDBInput_26";

					}
				} finally {
					if (rs_tDBInput_26 != null) {
						rs_tDBInput_26.close();
					}
					if (stmt_tDBInput_26 != null) {
						stmt_tDBInput_26.close();
					}
					if (conn_tDBInput_26 != null && !conn_tDBInput_26.isClosed()) {

						conn_tDBInput_26.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_26_NB_LINE", nb_line_tDBInput_26);

				ok_Hash.put("tDBInput_26", true);
				end_Hash.put("tDBInput_26", System.currentTimeMillis());

				/**
				 * [tDBInput_26 end ] stop
				 */

				/**
				 * [tAdvancedHash_row15 end ] start
				 */

				currentComponent = "tAdvancedHash_row15";

				tHash_Lookup_row15.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row15");
				}

				ok_Hash.put("tAdvancedHash_row15", true);
				end_Hash.put("tAdvancedHash_row15", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row15 end ] stop
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
				 * [tDBInput_26 finally ] start
				 */

				currentComponent = "tDBInput_26";

				/**
				 * [tDBInput_26 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row15 finally ] start
				 */

				currentComponent = "tAdvancedHash_row15";

				/**
				 * [tAdvancedHash_row15 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_26_SUBPROCESS_STATE", 1);
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
		final Base_mobile_orange Base_mobile_orangeClass = new Base_mobile_orange();

		int exitCode = Base_mobile_orangeClass.runJobInTOS(args);

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
			java.io.InputStream inContext = Base_mobile_orange.class.getClassLoader()
					.getResourceAsStream("orange/base_mobile_orange_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Base_mobile_orange.class.getClassLoader()
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
			tFileInputExcel_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_1) {
			globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Base_mobile_orange");
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
 * 418556 characters generated by Talend Open Studio for Data Integration on the
 * 13 mai 2022 à 22:18:14 CEST
 ************************************************************************************************/