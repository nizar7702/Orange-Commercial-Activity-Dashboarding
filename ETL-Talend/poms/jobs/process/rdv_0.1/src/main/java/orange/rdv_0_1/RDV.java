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

package orange.rdv_0_1;

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
 * Job: RDV Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class RDV implements TalendJob {

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
	private final String jobName = "RDV";
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
					RDV.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(RDV.this, new Object[] { e, currentComponent, globalMap });
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

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
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

	public void tDBInput_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_11_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_12_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_13_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_15_error(Exception exception, String errorComponent,
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

	public void tAdvancedHash_row6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row12_error(Exception exception, String errorComponent,
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

	public void tAdvancedHash_row11_error(Exception exception, String errorComponent,
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

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class orange_RDVStruct implements routines.system.IPersistableRow<orange_RDVStruct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];

		public Integer equipe_id;

		public Integer getEquipe_id() {
			return this.equipe_id;
		}

		public Integer statut_finale_id;

		public Integer getStatut_finale_id() {
			return this.statut_finale_id;
		}

		public Integer Categorie_id;

		public Integer getCategorie_id() {
			return this.Categorie_id;
		}

		public Integer gouvernorat_id;

		public Integer getGouvernorat_id() {
			return this.gouvernorat_id;
		}

		public Integer Raison_Sociale_id;

		public Integer getRaison_Sociale_id() {
			return this.Raison_Sociale_id;
		}

		public Integer membre_equipe_id;

		public Integer getMembre_equipe_id() {
			return this.membre_equipe_id;
		}

		public java.util.Date DIM_Date_id;

		public java.util.Date getDIM_Date_id() {
			return this.DIM_Date_id;
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.equipe_id = readInteger(dis);

					this.statut_finale_id = readInteger(dis);

					this.Categorie_id = readInteger(dis);

					this.gouvernorat_id = readInteger(dis);

					this.Raison_Sociale_id = readInteger(dis);

					this.membre_equipe_id = readInteger(dis);

					this.DIM_Date_id = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.equipe_id = readInteger(dis);

					this.statut_finale_id = readInteger(dis);

					this.Categorie_id = readInteger(dis);

					this.gouvernorat_id = readInteger(dis);

					this.Raison_Sociale_id = readInteger(dis);

					this.membre_equipe_id = readInteger(dis);

					this.DIM_Date_id = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.equipe_id, dos);

				// Integer

				writeInteger(this.statut_finale_id, dos);

				// Integer

				writeInteger(this.Categorie_id, dos);

				// Integer

				writeInteger(this.gouvernorat_id, dos);

				// Integer

				writeInteger(this.Raison_Sociale_id, dos);

				// Integer

				writeInteger(this.membre_equipe_id, dos);

				// java.util.Date

				writeDate(this.DIM_Date_id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.equipe_id, dos);

				// Integer

				writeInteger(this.statut_finale_id, dos);

				// Integer

				writeInteger(this.Categorie_id, dos);

				// Integer

				writeInteger(this.gouvernorat_id, dos);

				// Integer

				writeInteger(this.Raison_Sociale_id, dos);

				// Integer

				writeInteger(this.membre_equipe_id, dos);

				// java.util.Date

				writeDate(this.DIM_Date_id, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("equipe_id=" + String.valueOf(equipe_id));
			sb.append(",statut_finale_id=" + String.valueOf(statut_finale_id));
			sb.append(",Categorie_id=" + String.valueOf(Categorie_id));
			sb.append(",gouvernorat_id=" + String.valueOf(gouvernorat_id));
			sb.append(",Raison_Sociale_id=" + String.valueOf(Raison_Sociale_id));
			sb.append(",membre_equipe_id=" + String.valueOf(membre_equipe_id));
			sb.append(",DIM_Date_id=" + String.valueOf(DIM_Date_id));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(orange_RDVStruct other) {

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

	public static class conclusion3Struct implements routines.system.IPersistableRow<conclusion3Struct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];

		public String Sujet;

		public String getSujet() {
			return this.Sujet;
		}

		public String Cree_le;

		public String getCree_le() {
			return this.Cree_le;
		}

		public String Concernant;

		public String getConcernant() {
			return this.Concernant;
		}

		public String Cree_par;

		public String getCree_par() {
			return this.Cree_par;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public String Emplacement;

		public String getEmplacement() {
			return this.Emplacement;
		}

		public java.util.Date Heure_de_debut;

		public java.util.Date getHeure_de_debut() {
			return this.Heure_de_debut;
		}

		public String Heure_de_fin;

		public String getHeure_de_fin() {
			return this.Heure_de_fin;
		}

		public String Statut;

		public String getStatut() {
			return this.Statut;
		}

		public String Raison_sociale__Concernant;

		public String getRaison_sociale__Concernant() {
			return this.Raison_sociale__Concernant;
		}

		public String Delegation__Concernant;

		public String getDelegation__Concernant() {
			return this.Delegation__Concernant;
		}

		public String Gouvernorat__Concernant;

		public String getGouvernorat__Concernant() {
			return this.Gouvernorat__Concernant;
		}

		public String Jour_de_la_semaine;

		public String getJour_de_la_semaine() {
			return this.Jour_de_la_semaine;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Sujet = readString(dis);

					this.Cree_le = readString(dis);

					this.Concernant = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Emplacement = readString(dis);

					this.Heure_de_debut = readDate(dis);

					this.Heure_de_fin = readString(dis);

					this.Statut = readString(dis);

					this.Raison_sociale__Concernant = readString(dis);

					this.Delegation__Concernant = readString(dis);

					this.Gouvernorat__Concernant = readString(dis);

					this.Jour_de_la_semaine = readString(dis);

					this.Description = readString(dis);

					this.Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Sujet = readString(dis);

					this.Cree_le = readString(dis);

					this.Concernant = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Emplacement = readString(dis);

					this.Heure_de_debut = readDate(dis);

					this.Heure_de_fin = readString(dis);

					this.Statut = readString(dis);

					this.Raison_sociale__Concernant = readString(dis);

					this.Delegation__Concernant = readString(dis);

					this.Gouvernorat__Concernant = readString(dis);

					this.Jour_de_la_semaine = readString(dis);

					this.Description = readString(dis);

					this.Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Cree_le, dos);

				// String

				writeString(this.Concernant, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Emplacement, dos);

				// java.util.Date

				writeDate(this.Heure_de_debut, dos);

				// String

				writeString(this.Heure_de_fin, dos);

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Raison_sociale__Concernant, dos);

				// String

				writeString(this.Delegation__Concernant, dos);

				// String

				writeString(this.Gouvernorat__Concernant, dos);

				// String

				writeString(this.Jour_de_la_semaine, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Cree_le, dos);

				// String

				writeString(this.Concernant, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Emplacement, dos);

				// java.util.Date

				writeDate(this.Heure_de_debut, dos);

				// String

				writeString(this.Heure_de_fin, dos);

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Raison_sociale__Concernant, dos);

				// String

				writeString(this.Delegation__Concernant, dos);

				// String

				writeString(this.Gouvernorat__Concernant, dos);

				// String

				writeString(this.Jour_de_la_semaine, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Sujet=" + Sujet);
			sb.append(",Cree_le=" + Cree_le);
			sb.append(",Concernant=" + Concernant);
			sb.append(",Cree_par=" + Cree_par);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Emplacement=" + Emplacement);
			sb.append(",Heure_de_debut=" + String.valueOf(Heure_de_debut));
			sb.append(",Heure_de_fin=" + Heure_de_fin);
			sb.append(",Statut=" + Statut);
			sb.append(",Raison_sociale__Concernant=" + Raison_sociale__Concernant);
			sb.append(",Delegation__Concernant=" + Delegation__Concernant);
			sb.append(",Gouvernorat__Concernant=" + Gouvernorat__Concernant);
			sb.append(",Jour_de_la_semaine=" + Jour_de_la_semaine);
			sb.append(",Description=" + Description);
			sb.append(",Categorie=" + Categorie);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(conclusion3Struct other) {

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

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];

		public String Sujet;

		public String getSujet() {
			return this.Sujet;
		}

		public String Cree_le;

		public String getCree_le() {
			return this.Cree_le;
		}

		public String Concernant;

		public String getConcernant() {
			return this.Concernant;
		}

		public String Cree_par;

		public String getCree_par() {
			return this.Cree_par;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public String Emplacement;

		public String getEmplacement() {
			return this.Emplacement;
		}

		public java.util.Date Heure_de_debut;

		public java.util.Date getHeure_de_debut() {
			return this.Heure_de_debut;
		}

		public String Heure_de_fin;

		public String getHeure_de_fin() {
			return this.Heure_de_fin;
		}

		public String Statut;

		public String getStatut() {
			return this.Statut;
		}

		public String Raison_sociale__Concernant;

		public String getRaison_sociale__Concernant() {
			return this.Raison_sociale__Concernant;
		}

		public String Delegation__Concernant;

		public String getDelegation__Concernant() {
			return this.Delegation__Concernant;
		}

		public String Gouvernorat__Concernant;

		public String getGouvernorat__Concernant() {
			return this.Gouvernorat__Concernant;
		}

		public String Jour_de_la_semaine;

		public String getJour_de_la_semaine() {
			return this.Jour_de_la_semaine;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Sujet = readString(dis);

					this.Cree_le = readString(dis);

					this.Concernant = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Emplacement = readString(dis);

					this.Heure_de_debut = readDate(dis);

					this.Heure_de_fin = readString(dis);

					this.Statut = readString(dis);

					this.Raison_sociale__Concernant = readString(dis);

					this.Delegation__Concernant = readString(dis);

					this.Gouvernorat__Concernant = readString(dis);

					this.Jour_de_la_semaine = readString(dis);

					this.Description = readString(dis);

					this.Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Sujet = readString(dis);

					this.Cree_le = readString(dis);

					this.Concernant = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Emplacement = readString(dis);

					this.Heure_de_debut = readDate(dis);

					this.Heure_de_fin = readString(dis);

					this.Statut = readString(dis);

					this.Raison_sociale__Concernant = readString(dis);

					this.Delegation__Concernant = readString(dis);

					this.Gouvernorat__Concernant = readString(dis);

					this.Jour_de_la_semaine = readString(dis);

					this.Description = readString(dis);

					this.Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Cree_le, dos);

				// String

				writeString(this.Concernant, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Emplacement, dos);

				// java.util.Date

				writeDate(this.Heure_de_debut, dos);

				// String

				writeString(this.Heure_de_fin, dos);

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Raison_sociale__Concernant, dos);

				// String

				writeString(this.Delegation__Concernant, dos);

				// String

				writeString(this.Gouvernorat__Concernant, dos);

				// String

				writeString(this.Jour_de_la_semaine, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Cree_le, dos);

				// String

				writeString(this.Concernant, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Emplacement, dos);

				// java.util.Date

				writeDate(this.Heure_de_debut, dos);

				// String

				writeString(this.Heure_de_fin, dos);

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Raison_sociale__Concernant, dos);

				// String

				writeString(this.Delegation__Concernant, dos);

				// String

				writeString(this.Gouvernorat__Concernant, dos);

				// String

				writeString(this.Jour_de_la_semaine, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Sujet=" + Sujet);
			sb.append(",Cree_le=" + Cree_le);
			sb.append(",Concernant=" + Concernant);
			sb.append(",Cree_par=" + Cree_par);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Emplacement=" + Emplacement);
			sb.append(",Heure_de_debut=" + String.valueOf(Heure_de_debut));
			sb.append(",Heure_de_fin=" + Heure_de_fin);
			sb.append(",Statut=" + Statut);
			sb.append(",Raison_sociale__Concernant=" + Raison_sociale__Concernant);
			sb.append(",Delegation__Concernant=" + Delegation__Concernant);
			sb.append(",Gouvernorat__Concernant=" + Gouvernorat__Concernant);
			sb.append(",Jour_de_la_semaine=" + Jour_de_la_semaine);
			sb.append(",Description=" + Description);
			sb.append(",Categorie=" + Categorie);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

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
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];

		public String Sujet;

		public String getSujet() {
			return this.Sujet;
		}

		public String Cree_le;

		public String getCree_le() {
			return this.Cree_le;
		}

		public String Concernant;

		public String getConcernant() {
			return this.Concernant;
		}

		public String Cree_par;

		public String getCree_par() {
			return this.Cree_par;
		}

		public String Equipe;

		public String getEquipe() {
			return this.Equipe;
		}

		public String Emplacement;

		public String getEmplacement() {
			return this.Emplacement;
		}

		public java.util.Date Heure_de_debut;

		public java.util.Date getHeure_de_debut() {
			return this.Heure_de_debut;
		}

		public String Heure_de_fin;

		public String getHeure_de_fin() {
			return this.Heure_de_fin;
		}

		public String Statut;

		public String getStatut() {
			return this.Statut;
		}

		public String Raison_sociale__Concernant;

		public String getRaison_sociale__Concernant() {
			return this.Raison_sociale__Concernant;
		}

		public String Delegation__Concernant;

		public String getDelegation__Concernant() {
			return this.Delegation__Concernant;
		}

		public String Gouvernorat__Concernant;

		public String getGouvernorat__Concernant() {
			return this.Gouvernorat__Concernant;
		}

		public String Jour_de_la_semaine;

		public String getJour_de_la_semaine() {
			return this.Jour_de_la_semaine;
		}

		public String Description;

		public String getDescription() {
			return this.Description;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Sujet = readString(dis);

					this.Cree_le = readString(dis);

					this.Concernant = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Emplacement = readString(dis);

					this.Heure_de_debut = readDate(dis);

					this.Heure_de_fin = readString(dis);

					this.Statut = readString(dis);

					this.Raison_sociale__Concernant = readString(dis);

					this.Delegation__Concernant = readString(dis);

					this.Gouvernorat__Concernant = readString(dis);

					this.Jour_de_la_semaine = readString(dis);

					this.Description = readString(dis);

					this.Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Sujet = readString(dis);

					this.Cree_le = readString(dis);

					this.Concernant = readString(dis);

					this.Cree_par = readString(dis);

					this.Equipe = readString(dis);

					this.Emplacement = readString(dis);

					this.Heure_de_debut = readDate(dis);

					this.Heure_de_fin = readString(dis);

					this.Statut = readString(dis);

					this.Raison_sociale__Concernant = readString(dis);

					this.Delegation__Concernant = readString(dis);

					this.Gouvernorat__Concernant = readString(dis);

					this.Jour_de_la_semaine = readString(dis);

					this.Description = readString(dis);

					this.Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Cree_le, dos);

				// String

				writeString(this.Concernant, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Emplacement, dos);

				// java.util.Date

				writeDate(this.Heure_de_debut, dos);

				// String

				writeString(this.Heure_de_fin, dos);

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Raison_sociale__Concernant, dos);

				// String

				writeString(this.Delegation__Concernant, dos);

				// String

				writeString(this.Gouvernorat__Concernant, dos);

				// String

				writeString(this.Jour_de_la_semaine, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Sujet, dos);

				// String

				writeString(this.Cree_le, dos);

				// String

				writeString(this.Concernant, dos);

				// String

				writeString(this.Cree_par, dos);

				// String

				writeString(this.Equipe, dos);

				// String

				writeString(this.Emplacement, dos);

				// java.util.Date

				writeDate(this.Heure_de_debut, dos);

				// String

				writeString(this.Heure_de_fin, dos);

				// String

				writeString(this.Statut, dos);

				// String

				writeString(this.Raison_sociale__Concernant, dos);

				// String

				writeString(this.Delegation__Concernant, dos);

				// String

				writeString(this.Gouvernorat__Concernant, dos);

				// String

				writeString(this.Jour_de_la_semaine, dos);

				// String

				writeString(this.Description, dos);

				// String

				writeString(this.Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Sujet=" + Sujet);
			sb.append(",Cree_le=" + Cree_le);
			sb.append(",Concernant=" + Concernant);
			sb.append(",Cree_par=" + Cree_par);
			sb.append(",Equipe=" + Equipe);
			sb.append(",Emplacement=" + Emplacement);
			sb.append(",Heure_de_debut=" + String.valueOf(Heure_de_debut));
			sb.append(",Heure_de_fin=" + Heure_de_fin);
			sb.append(",Statut=" + Statut);
			sb.append(",Raison_sociale__Concernant=" + Raison_sociale__Concernant);
			sb.append(",Delegation__Concernant=" + Delegation__Concernant);
			sb.append(",Gouvernorat__Concernant=" + Gouvernorat__Concernant);
			sb.append(",Jour_de_la_semaine=" + Jour_de_la_semaine);
			sb.append(",Description=" + Description);
			sb.append(",Categorie=" + Categorie);
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

				tDBInput_10Process(globalMap);
				tDBInput_11Process(globalMap);
				tDBInput_12Process(globalMap);
				tDBInput_13Process(globalMap);
				tDBInput_15Process(globalMap);
				tDBInput_17Process(globalMap);

				row2Struct row2 = new row2Struct();
				conclusion3Struct conclusion3 = new conclusion3Struct();
				orange_RDVStruct orange_RDV = new orange_RDVStruct();

				/**
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "orange_RDV");
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
						"enc:routine.encryption.key.v1:kFL9Rj8td5QyA1dEUOxHygUmIm4Gk7wkqFu2JEg68IyljSx60O0=");

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
					tableName_tDBOutput_2 = "fact_table_rdv";
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "].[" + "fact_table_rdv";
				}
				int count_tDBOutput_2 = 0;

				String insert_tDBOutput_2 = "INSERT INTO [" + tableName_tDBOutput_2
						+ "] ([equipe_id],[statut_finale_id],[Catégorie_id],[gouvernorat_id],[Raison_Sociale_id],[membre_équipe_id],[DIM_Date_id]) VALUES (?,?,?,?,?,?,?)";
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
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "conclusion3");
				}

				int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) globalMap
						.get("tHash_Lookup_row9"));

				row9Struct row9HashKey = new row9Struct();
				row9Struct row9Default = new row9Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row11Struct> tHash_Lookup_row11 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row11Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row11Struct>) globalMap
						.get("tHash_Lookup_row11"));

				row11Struct row11HashKey = new row11Struct();
				row11Struct row11Default = new row11Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) globalMap
						.get("tHash_Lookup_row3"));

				row3Struct row3HashKey = new row3Struct();
				row3Struct row3Default = new row3Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) globalMap
						.get("tHash_Lookup_row4"));

				row4Struct row4HashKey = new row4Struct();
				row4Struct row4Default = new row4Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct> tHash_Lookup_row12 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct>) globalMap
						.get("tHash_Lookup_row12"));

				row12Struct row12HashKey = new row12Struct();
				row12Struct row12Default = new row12Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) globalMap
						.get("tHash_Lookup_row6"));

				row6Struct row6HashKey = new row6Struct();
				row6Struct row6Default = new row6Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				orange_RDVStruct orange_RDV_tmp = new orange_RDVStruct();
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
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
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
				conclusion3Struct conclusion3_tmp = new conclusion3Struct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:I5y6iZnFLpmZ+lRPpZOw+LrYHEchaXCfeqCK+g==");
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

				Object source_tFileInputExcel_1 = "C:/Users/DELL/Downloads/suivi rdv slide test (Enregistré automatiquement) VF (1).xlsx";
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
					sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1,
							"{40AAA5D9-FC5C-EC11-80E1-005056", false));
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
							row2 = null;
							int tempRowLength_tFileInputExcel_1 = 15;

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
							row2 = new row2Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Sujet";

									row2.Sujet = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Sujet = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Cree_le";

									row2.Cree_le = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Cree_le = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 2;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Concernant";

									row2.Concernant = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Concernant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 3;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Cree_par";

									row2.Cree_par = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Cree_par = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 4;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Equipe";

									row2.Equipe = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Equipe = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 5;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Emplacement";

									row2.Emplacement = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Emplacement = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 6;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Heure_de_debut";

									if (6 < actual_end_column_tFileInputExcel_1) {
										try {
											if (row_tFileInputExcel_1
													.getCell(columnIndex_tFileInputExcel_1
															+ start_column_tFileInputExcel_1)
													.getCellType() == org.apache.poi.ss.usermodel.CellType.NUMERIC
													&& org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(
															row_tFileInputExcel_1.getCell(columnIndex_tFileInputExcel_1
																	+ start_column_tFileInputExcel_1))) {
												row2.Heure_de_debut = row_tFileInputExcel_1.getCell(
														columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1)
														.getDateCellValue();
											} else {
												java.util.Date tempDate_tFileInputExcel_1 = ParserUtils.parseTo_Date(
														temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1],
														"dd/MM/yyyy");
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
													row2.Heure_de_debut = tempDate_tFileInputExcel_1;
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
									row2.Heure_de_debut = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 7;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Heure_de_fin";

									row2.Heure_de_fin = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Heure_de_fin = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 8;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Statut";

									row2.Statut = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Statut = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 9;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Raison_sociale__Concernant";

									row2.Raison_sociale__Concernant = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Raison_sociale__Concernant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 10;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Delegation__Concernant";

									row2.Delegation__Concernant = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Delegation__Concernant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 11;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Gouvernorat__Concernant";

									row2.Gouvernorat__Concernant = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Gouvernorat__Concernant = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 12;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Jour_de_la_semaine";

									row2.Jour_de_la_semaine = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Jour_de_la_semaine = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 13;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Description";

									row2.Description = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Description = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 14;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Categorie";

									row2.Categorie = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row2.Categorie = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								System.err.println(e.getMessage());
								row2 = null;
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
// Start of branch "row2"
							if (row2 != null) {

								/**
								 * [tMap_3 main ] start
								 */

								currentComponent = "tMap_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row2"

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

									conclusion3 = null;

// # Output table : 'conclusion3'
									conclusion3_tmp.Sujet = row2.Sujet;
									conclusion3_tmp.Cree_le = row2.Cree_le;
									conclusion3_tmp.Concernant = row2.Concernant;
									conclusion3_tmp.Cree_par = StringHandling
											.CHANGE(StringHandling.UPCASE(row2.Cree_par), "ADELWAHED", "ABDELWAHED");
									conclusion3_tmp.Equipe = row2.Equipe;
									conclusion3_tmp.Emplacement = row2.Emplacement;
									conclusion3_tmp.Heure_de_debut = row2.Heure_de_debut;
									conclusion3_tmp.Heure_de_fin = row2.Heure_de_fin;
									conclusion3_tmp.Statut = row2.Statut;
									conclusion3_tmp.Raison_sociale__Concernant = row2.Raison_sociale__Concernant;
									conclusion3_tmp.Delegation__Concernant = row2.Delegation__Concernant;
									conclusion3_tmp.Gouvernorat__Concernant = StringHandling
											.TRIM(StringHandling.UPCASE(row2.Gouvernorat__Concernant));
									conclusion3_tmp.Jour_de_la_semaine = row2.Jour_de_la_semaine;
									conclusion3_tmp.Description = row2.Description;
									conclusion3_tmp.Categorie = row2.Categorie;
									conclusion3 = conclusion3_tmp;
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
// Start of branch "conclusion3"
								if (conclusion3 != null) {

									/**
									 * [tMap_2 main ] start
									 */

									currentComponent = "tMap_2";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "conclusion3"

										);
									}

									boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

									// ###############################
									// # Input tables (lookups)
									boolean rejectedInnerJoin_tMap_2 = false;
									boolean mainRowRejected_tMap_2 = false;

									///////////////////////////////////////////////
									// Starting Lookup Table "row9"
									///////////////////////////////////////////////

									boolean forceLooprow9 = false;

									row9Struct row9ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row9HashKey.Categorie = conclusion3.Categorie;

										row9HashKey.hashCodeDirty = true;

										tHash_Lookup_row9.lookup(row9HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row9 != null && tHash_Lookup_row9.getCount(row9HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row9'
										// and it contains more one result from keys : row9.Categorie = '" +
										// row9HashKey.Categorie + "'");
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
									// Starting Lookup Table "row11"
									///////////////////////////////////////////////

									boolean forceLooprow11 = false;

									row11Struct row11ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row11HashKey.Client_Prospect = conclusion3.Raison_sociale__Concernant;

										row11HashKey.hashCodeDirty = true;

										tHash_Lookup_row11.lookup(row11HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row11 != null && tHash_Lookup_row11.getCount(row11HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row11' and it contains more one result from keys : row11.Client_Prospect =
										// '" + row11HashKey.Client_Prospect + "'");
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
									// Starting Lookup Table "row3"
									///////////////////////////////////////////////

									boolean forceLooprow3 = false;

									row3Struct row3ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row3HashKey.Equipe = conclusion3.Equipe;

										row3HashKey.hashCodeDirty = true;

										tHash_Lookup_row3.lookup(row3HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3'
										// and it contains more one result from keys : row3.Equipe = '" +
										// row3HashKey.Equipe + "'");
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
									// Starting Lookup Table "row4"
									///////////////////////////////////////////////

									boolean forceLooprow4 = false;

									row4Struct row4ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row4HashKey.Gouvernorat = conclusion3.Gouvernorat__Concernant;

										row4HashKey.hashCodeDirty = true;

										tHash_Lookup_row4.lookup(row4HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4'
										// and it contains more one result from keys : row4.Gouvernorat = '" +
										// row4HashKey.Gouvernorat + "'");
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
									// Starting Lookup Table "row12"
									///////////////////////////////////////////////

									boolean forceLooprow12 = false;

									row12Struct row12ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row12HashKey.Account_Manager__Client_Prospect = conclusion3.Cree_par;

										row12HashKey.hashCodeDirty = true;

										tHash_Lookup_row12.lookup(row12HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row12 != null && tHash_Lookup_row12.getCount(row12HashKey) > 1) { // G
																														// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
										// 'row12' and it contains more one result from keys :
										// row12.Account_Manager__Client_Prospect = '" +
										// row12HashKey.Account_Manager__Client_Prospect + "'");
									} // G 071

									row12Struct row12 = null;

									row12Struct fromLookup_row12 = null;
									row12 = row12Default;

									if (tHash_Lookup_row12 != null && tHash_Lookup_row12.hasNext()) { // G 099

										fromLookup_row12 = tHash_Lookup_row12.next();

									} // G 099

									if (fromLookup_row12 != null) {
										row12 = fromLookup_row12;
									}

									///////////////////////////////////////////////
									// Starting Lookup Table "row6"
									///////////////////////////////////////////////

									boolean forceLooprow6 = false;

									row6Struct row6ObjectFromLookup = null;

									if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

										hasCasePrimitiveKeyWithNull_tMap_2 = false;

										row6HashKey.Staut_Final = conclusion3.Statut;

										row6HashKey.hashCodeDirty = true;

										tHash_Lookup_row6.lookup(row6HashKey);

									} // G_TM_M_020

									if (tHash_Lookup_row6 != null && tHash_Lookup_row6.getCount(row6HashKey) > 1) { // G
																													// 071

										// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row6'
										// and it contains more one result from keys : row6.Staut_Final = '" +
										// row6HashKey.Staut_Final + "'");
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

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
										// ###############################
										// # Output tables

										orange_RDV = null;

// # Output table : 'orange_RDV'
										orange_RDV_tmp.equipe_id = row3.equipe_id;
										orange_RDV_tmp.statut_finale_id = row6.statu_final_id;
										orange_RDV_tmp.Categorie_id = row9.Categorie_id;
										orange_RDV_tmp.gouvernorat_id = row4.gouvernorat_id;
										orange_RDV_tmp.Raison_Sociale_id = row11.Client_Prospect_id;
										orange_RDV_tmp.membre_equipe_id = row12.membre_equipe_id;
										orange_RDV_tmp.DIM_Date_id = conclusion3.Heure_de_debut;
										orange_RDV = orange_RDV_tmp;
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
// Start of branch "orange_RDV"
									if (orange_RDV != null) {

										/**
										 * [tDBOutput_2 main ] start
										 */

										currentComponent = "tDBOutput_2";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "orange_RDV"

											);
										}

										whetherReject_tDBOutput_2 = false;
										if (orange_RDV.equipe_id == null) {
											pstmt_tDBOutput_2.setNull(1, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(1, orange_RDV.equipe_id);
										}

										if (orange_RDV.statut_finale_id == null) {
											pstmt_tDBOutput_2.setNull(2, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(2, orange_RDV.statut_finale_id);
										}

										if (orange_RDV.Categorie_id == null) {
											pstmt_tDBOutput_2.setNull(3, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(3, orange_RDV.Categorie_id);
										}

										if (orange_RDV.gouvernorat_id == null) {
											pstmt_tDBOutput_2.setNull(4, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(4, orange_RDV.gouvernorat_id);
										}

										if (orange_RDV.Raison_Sociale_id == null) {
											pstmt_tDBOutput_2.setNull(5, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(5, orange_RDV.Raison_Sociale_id);
										}

										if (orange_RDV.membre_equipe_id == null) {
											pstmt_tDBOutput_2.setNull(6, java.sql.Types.INTEGER);
										} else {
											pstmt_tDBOutput_2.setInt(6, orange_RDV.membre_equipe_id);
										}

										if (orange_RDV.DIM_Date_id != null) {
											pstmt_tDBOutput_2.setTimestamp(7,
													new java.sql.Timestamp(orange_RDV.DIM_Date_id.getTime()));
										} else {
											pstmt_tDBOutput_2.setNull(7, java.sql.Types.TIMESTAMP);
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

									} // End of branch "orange_RDV"

									/**
									 * [tMap_2 process_data_end ] start
									 */

									currentComponent = "tMap_2";

									/**
									 * [tMap_2 process_data_end ] stop
									 */

								} // End of branch "conclusion3"

								/**
								 * [tMap_3 process_data_end ] start
								 */

								currentComponent = "tMap_3";

								/**
								 * [tMap_3 process_data_end ] stop
								 */

							} // End of branch "row2"

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
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
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
				if (tHash_Lookup_row9 != null) {
					tHash_Lookup_row9.endGet();
				}
				globalMap.remove("tHash_Lookup_row9");

				if (tHash_Lookup_row11 != null) {
					tHash_Lookup_row11.endGet();
				}
				globalMap.remove("tHash_Lookup_row11");

				if (tHash_Lookup_row3 != null) {
					tHash_Lookup_row3.endGet();
				}
				globalMap.remove("tHash_Lookup_row3");

				if (tHash_Lookup_row4 != null) {
					tHash_Lookup_row4.endGet();
				}
				globalMap.remove("tHash_Lookup_row4");

				if (tHash_Lookup_row12 != null) {
					tHash_Lookup_row12.endGet();
				}
				globalMap.remove("tHash_Lookup_row12");

				if (tHash_Lookup_row6 != null) {
					tHash_Lookup_row6.endGet();
				}
				globalMap.remove("tHash_Lookup_row6");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "conclusion3");
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "orange_RDV");
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
			globalMap.remove("tHash_Lookup_row6");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row12");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row4");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row3");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row11");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row9");

			try {

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
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

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableComparableLookupRow<row6Struct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];
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
			final row6Struct other = (row6Struct) obj;

			if (this.Staut_Final == null) {
				if (other.Staut_Final != null)
					return false;

			} else if (!this.Staut_Final.equals(other.Staut_Final))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other.statu_final_id = this.statu_final_id;
			other.Staut_Final = this.Staut_Final;

		}

		public void copyKeysDataTo(row6Struct other) {

			other.Staut_Final = this.Staut_Final;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Staut_Final = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

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
		public int compareTo(row6Struct other) {

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

	public void tDBInput_10Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_10_SUBPROCESS_STATE", 0);

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
				// source node:tDBInput_10 - inputs:(after_tFileInputExcel_1)
				// outputs:(row6,row6) | target node:tAdvancedHash_row6 - inputs:(row6)
				// outputs:()
				// linked node: tMap_2 - inputs:(conclusion3,row6,row12,row4,row3,row11,row9)
				// outputs:(orange_RDV)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row6 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row6Struct>getLookup(matchingModeEnum_row6);

				globalMap.put("tHash_Lookup_row6", tHash_Lookup_row6);

				/**
				 * [tAdvancedHash_row6 begin ] stop
				 */

				/**
				 * [tDBInput_10 begin ] start
				 */

				ok_Hash.put("tDBInput_10", false);
				start_Hash.put("tDBInput_10", System.currentTimeMillis());

				currentComponent = "tDBInput_10";

				int tos_count_tDBInput_10 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_10 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_10 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_10 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_10, talendToDBArray_tDBInput_10);
				int nb_line_tDBInput_10 = 0;
				java.sql.Connection conn_tDBInput_10 = null;
				String driverClass_tDBInput_10 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_10 = java.lang.Class.forName(driverClass_tDBInput_10);
				String dbUser_tDBInput_10 = "sa";

				final String decryptedPassword_tDBInput_10 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:E/gCuZJWJBDJCtX0GL+VgaWSnPbkIwMWOaepl1BBMYnr7Em3W34=");

				String dbPwd_tDBInput_10 = decryptedPassword_tDBInput_10;

				String port_tDBInput_10 = "1433";
				String dbname_tDBInput_10 = "orange_DW__";
				String url_tDBInput_10 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_10)) {
					url_tDBInput_10 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_10)) {
					url_tDBInput_10 += "//" + "orange_DW__";
				}
				url_tDBInput_10 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_10 = "dbo";

				conn_tDBInput_10 = java.sql.DriverManager.getConnection(url_tDBInput_10, dbUser_tDBInput_10,
						dbPwd_tDBInput_10);

				java.sql.Statement stmt_tDBInput_10 = conn_tDBInput_10.createStatement();

				String dbquery_tDBInput_10 = "SELECT dbo.Dim_statu_final.statu_final_id,\n		dbo.Dim_statu_final.Staut_Final\nFROM	dbo.Dim_statu_final";

				globalMap.put("tDBInput_10_QUERY", dbquery_tDBInput_10);
				java.sql.ResultSet rs_tDBInput_10 = null;

				try {
					rs_tDBInput_10 = stmt_tDBInput_10.executeQuery(dbquery_tDBInput_10);
					java.sql.ResultSetMetaData rsmd_tDBInput_10 = rs_tDBInput_10.getMetaData();
					int colQtyInRs_tDBInput_10 = rsmd_tDBInput_10.getColumnCount();

					String tmpContent_tDBInput_10 = null;

					while (rs_tDBInput_10.next()) {
						nb_line_tDBInput_10++;

						if (colQtyInRs_tDBInput_10 < 1) {
							row6.statu_final_id = 0;
						} else {

							row6.statu_final_id = rs_tDBInput_10.getInt(1);
							if (rs_tDBInput_10.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_10 < 2) {
							row6.Staut_Final = null;
						} else {

							tmpContent_tDBInput_10 = rs_tDBInput_10.getString(2);
							if (tmpContent_tDBInput_10 != null) {
								if (talendToDBList_tDBInput_10.contains(
										rsmd_tDBInput_10.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row6.Staut_Final = FormatterUtils.formatUnwithE(tmpContent_tDBInput_10);
								} else {
									row6.Staut_Final = tmpContent_tDBInput_10;
								}
							} else {
								row6.Staut_Final = null;
							}
						}

						/**
						 * [tDBInput_10 begin ] stop
						 */

						/**
						 * [tDBInput_10 main ] start
						 */

						currentComponent = "tDBInput_10";

						tos_count_tDBInput_10++;

						/**
						 * [tDBInput_10 main ] stop
						 */

						/**
						 * [tDBInput_10 process_data_begin ] start
						 */

						currentComponent = "tDBInput_10";

						/**
						 * [tDBInput_10 process_data_begin ] stop
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

						row6_HashRow.statu_final_id = row6.statu_final_id;

						row6_HashRow.Staut_Final = row6.Staut_Final;

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
						 * [tDBInput_10 process_data_end ] start
						 */

						currentComponent = "tDBInput_10";

						/**
						 * [tDBInput_10 process_data_end ] stop
						 */

						/**
						 * [tDBInput_10 end ] start
						 */

						currentComponent = "tDBInput_10";

					}
				} finally {
					if (rs_tDBInput_10 != null) {
						rs_tDBInput_10.close();
					}
					if (stmt_tDBInput_10 != null) {
						stmt_tDBInput_10.close();
					}
					if (conn_tDBInput_10 != null && !conn_tDBInput_10.isClosed()) {

						conn_tDBInput_10.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_10_NB_LINE", nb_line_tDBInput_10);

				ok_Hash.put("tDBInput_10", true);
				end_Hash.put("tDBInput_10", System.currentTimeMillis());

				/**
				 * [tDBInput_10 end ] stop
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
				 * [tDBInput_10 finally ] start
				 */

				currentComponent = "tDBInput_10";

				/**
				 * [tDBInput_10 finally ] stop
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

		globalMap.put("tDBInput_10_SUBPROCESS_STATE", 1);
	}

	public static class row12Struct implements routines.system.IPersistableComparableLookupRow<row12Struct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];
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
			final row12Struct other = (row12Struct) obj;

			if (this.Account_Manager__Client_Prospect == null) {
				if (other.Account_Manager__Client_Prospect != null)
					return false;

			} else if (!this.Account_Manager__Client_Prospect.equals(other.Account_Manager__Client_Prospect))

				return false;

			return true;
		}

		public void copyDataTo(row12Struct other) {

			other.membre_equipe_id = this.membre_equipe_id;
			other.Account_Manager__Client_Prospect = this.Account_Manager__Client_Prospect;

		}

		public void copyKeysDataTo(row12Struct other) {

			other.Account_Manager__Client_Prospect = this.Account_Manager__Client_Prospect;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Account_Manager__Client_Prospect = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

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
		public int compareTo(row12Struct other) {

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

	public void tDBInput_11Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_11_SUBPROCESS_STATE", 0);

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

				row12Struct row12 = new row12Struct();

				/**
				 * [tAdvancedHash_row12 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row12", false);
				start_Hash.put("tAdvancedHash_row12", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row12";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row12");
				}

				int tos_count_tAdvancedHash_row12 = 0;

				// connection name:row12
				// source node:tDBInput_11 - inputs:(after_tFileInputExcel_1)
				// outputs:(row12,row12) | target node:tAdvancedHash_row12 - inputs:(row12)
				// outputs:()
				// linked node: tMap_2 - inputs:(conclusion3,row6,row12,row4,row3,row11,row9)
				// outputs:(orange_RDV)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row12 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct> tHash_Lookup_row12 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row12Struct>getLookup(matchingModeEnum_row12);

				globalMap.put("tHash_Lookup_row12", tHash_Lookup_row12);

				/**
				 * [tAdvancedHash_row12 begin ] stop
				 */

				/**
				 * [tDBInput_11 begin ] start
				 */

				ok_Hash.put("tDBInput_11", false);
				start_Hash.put("tDBInput_11", System.currentTimeMillis());

				currentComponent = "tDBInput_11";

				int tos_count_tDBInput_11 = 0;

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_11 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_11 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_11 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_11, talendToDBArray_tDBInput_11);
				int nb_line_tDBInput_11 = 0;
				java.sql.Connection conn_tDBInput_11 = null;
				String driverClass_tDBInput_11 = "net.sourceforge.jtds.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_11 = java.lang.Class.forName(driverClass_tDBInput_11);
				String dbUser_tDBInput_11 = "sa";

				final String decryptedPassword_tDBInput_11 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:j9q6oPywn2xLwrKcwQrThKF6pUWnvfk0lghhCbTpK3Lw+LZ0ZXc=");

				String dbPwd_tDBInput_11 = decryptedPassword_tDBInput_11;

				String port_tDBInput_11 = "1433";
				String dbname_tDBInput_11 = "orange_DW__";
				String url_tDBInput_11 = "jdbc:jtds:sqlserver://" + "DESKTOP-6RL9E91";
				if (!"".equals(port_tDBInput_11)) {
					url_tDBInput_11 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_11)) {
					url_tDBInput_11 += "//" + "orange_DW__";
				}
				url_tDBInput_11 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_11 = "dbo";

				conn_tDBInput_11 = java.sql.DriverManager.getConnection(url_tDBInput_11, dbUser_tDBInput_11,
						dbPwd_tDBInput_11);

				java.sql.Statement stmt_tDBInput_11 = conn_tDBInput_11.createStatement();

				String dbquery_tDBInput_11 = "SELECT dbo.Dim_membre.\"membre_équipe_id\",\n		dbo.Dim_membre.Account_Manager__Client_Prospect\nFROM	dbo.Dim_membre";

				globalMap.put("tDBInput_11_QUERY", dbquery_tDBInput_11);
				java.sql.ResultSet rs_tDBInput_11 = null;

				try {
					rs_tDBInput_11 = stmt_tDBInput_11.executeQuery(dbquery_tDBInput_11);
					java.sql.ResultSetMetaData rsmd_tDBInput_11 = rs_tDBInput_11.getMetaData();
					int colQtyInRs_tDBInput_11 = rsmd_tDBInput_11.getColumnCount();

					String tmpContent_tDBInput_11 = null;

					while (rs_tDBInput_11.next()) {
						nb_line_tDBInput_11++;

						if (colQtyInRs_tDBInput_11 < 1) {
							row12.membre_equipe_id = 0;
						} else {

							row12.membre_equipe_id = rs_tDBInput_11.getInt(1);
							if (rs_tDBInput_11.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_11 < 2) {
							row12.Account_Manager__Client_Prospect = null;
						} else {

							tmpContent_tDBInput_11 = rs_tDBInput_11.getString(2);
							if (tmpContent_tDBInput_11 != null) {
								if (talendToDBList_tDBInput_11.contains(
										rsmd_tDBInput_11.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row12.Account_Manager__Client_Prospect = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_11);
								} else {
									row12.Account_Manager__Client_Prospect = tmpContent_tDBInput_11;
								}
							} else {
								row12.Account_Manager__Client_Prospect = null;
							}
						}

						/**
						 * [tDBInput_11 begin ] stop
						 */

						/**
						 * [tDBInput_11 main ] start
						 */

						currentComponent = "tDBInput_11";

						tos_count_tDBInput_11++;

						/**
						 * [tDBInput_11 main ] stop
						 */

						/**
						 * [tDBInput_11 process_data_begin ] start
						 */

						currentComponent = "tDBInput_11";

						/**
						 * [tDBInput_11 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row12 main ] start
						 */

						currentComponent = "tAdvancedHash_row12";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row12"

							);
						}

						row12Struct row12_HashRow = new row12Struct();

						row12_HashRow.membre_equipe_id = row12.membre_equipe_id;

						row12_HashRow.Account_Manager__Client_Prospect = row12.Account_Manager__Client_Prospect;

						tHash_Lookup_row12.put(row12_HashRow);

						tos_count_tAdvancedHash_row12++;

						/**
						 * [tAdvancedHash_row12 main ] stop
						 */

						/**
						 * [tAdvancedHash_row12 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row12";

						/**
						 * [tAdvancedHash_row12 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row12 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row12";

						/**
						 * [tAdvancedHash_row12 process_data_end ] stop
						 */

						/**
						 * [tDBInput_11 process_data_end ] start
						 */

						currentComponent = "tDBInput_11";

						/**
						 * [tDBInput_11 process_data_end ] stop
						 */

						/**
						 * [tDBInput_11 end ] start
						 */

						currentComponent = "tDBInput_11";

					}
				} finally {
					if (rs_tDBInput_11 != null) {
						rs_tDBInput_11.close();
					}
					if (stmt_tDBInput_11 != null) {
						stmt_tDBInput_11.close();
					}
					if (conn_tDBInput_11 != null && !conn_tDBInput_11.isClosed()) {

						conn_tDBInput_11.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}
				}
				globalMap.put("tDBInput_11_NB_LINE", nb_line_tDBInput_11);

				ok_Hash.put("tDBInput_11", true);
				end_Hash.put("tDBInput_11", System.currentTimeMillis());

				/**
				 * [tDBInput_11 end ] stop
				 */

				/**
				 * [tAdvancedHash_row12 end ] start
				 */

				currentComponent = "tAdvancedHash_row12";

				tHash_Lookup_row12.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row12");
				}

				ok_Hash.put("tAdvancedHash_row12", true);
				end_Hash.put("tAdvancedHash_row12", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row12 end ] stop
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
				 * [tDBInput_11 finally ] start
				 */

				currentComponent = "tDBInput_11";

				/**
				 * [tDBInput_11 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row12 finally ] start
				 */

				currentComponent = "tAdvancedHash_row12";

				/**
				 * [tAdvancedHash_row12 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_11_SUBPROCESS_STATE", 1);
	}

	public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];
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
			final row4Struct other = (row4Struct) obj;

			if (this.Gouvernorat == null) {
				if (other.Gouvernorat != null)
					return false;

			} else if (!this.Gouvernorat.equals(other.Gouvernorat))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.gouvernorat_id = this.gouvernorat_id;
			other.Gouvernorat = this.Gouvernorat;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.Gouvernorat = this.Gouvernorat;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Gouvernorat = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

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
		public int compareTo(row4Struct other) {

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
				// source node:tDBInput_12 - inputs:(after_tFileInputExcel_1)
				// outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4)
				// outputs:()
				// linked node: tMap_2 - inputs:(conclusion3,row6,row12,row4,row3,row11,row9)
				// outputs:(orange_RDV)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row4Struct>getLookup(matchingModeEnum_row4);

				globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);

				/**
				 * [tAdvancedHash_row4 begin ] stop
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
						"enc:routine.encryption.key.v1:cRGQwKYklO6xzxZ19HRlzv0dRR0FaVj5kOqysRaJhkz4mkPYoqA=");

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

				String dbquery_tDBInput_12 = "SELECT dbo.Dim_gouvernorat.gouvernorat_id,\n		dbo.Dim_gouvernorat.Gouvernorat\nFROM	dbo.Dim_gouvernorat";

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
							row4.gouvernorat_id = 0;
						} else {

							row4.gouvernorat_id = rs_tDBInput_12.getInt(1);
							if (rs_tDBInput_12.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_12 < 2) {
							row4.Gouvernorat = null;
						} else {

							tmpContent_tDBInput_12 = rs_tDBInput_12.getString(2);
							if (tmpContent_tDBInput_12 != null) {
								if (talendToDBList_tDBInput_12.contains(
										rsmd_tDBInput_12.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row4.Gouvernorat = FormatterUtils.formatUnwithE(tmpContent_tDBInput_12);
								} else {
									row4.Gouvernorat = tmpContent_tDBInput_12;
								}
							} else {
								row4.Gouvernorat = null;
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
						 * [tAdvancedHash_row4 main ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row4"

							);
						}

						row4Struct row4_HashRow = new row4Struct();

						row4_HashRow.gouvernorat_id = row4.gouvernorat_id;

						row4_HashRow.Gouvernorat = row4.Gouvernorat;

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
				 * [tDBInput_12 finally ] start
				 */

				currentComponent = "tDBInput_12";

				/**
				 * [tDBInput_12 finally ] stop
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

		globalMap.put("tDBInput_12_SUBPROCESS_STATE", 1);
	}

	public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];
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
			final row3Struct other = (row3Struct) obj;

			if (this.Equipe == null) {
				if (other.Equipe != null)
					return false;

			} else if (!this.Equipe.equals(other.Equipe))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.equipe_id = this.equipe_id;
			other.Equipe = this.Equipe;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.Equipe = this.Equipe;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Equipe = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

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
		public int compareTo(row3Struct other) {

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
				// source node:tDBInput_13 - inputs:(after_tFileInputExcel_1)
				// outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3)
				// outputs:()
				// linked node: tMap_2 - inputs:(conclusion3,row6,row12,row4,row3,row11,row9)
				// outputs:(orange_RDV)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row3Struct>getLookup(matchingModeEnum_row3);

				globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);

				/**
				 * [tAdvancedHash_row3 begin ] stop
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
						"enc:routine.encryption.key.v1:FjtBLqA3bwvIQL3bj3arM50UY4Vt/0KBpz2LGD3mC1v3vCw8Pn8=");

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

				String dbquery_tDBInput_13 = "SELECT dbo.Dim_equipe.equipe_id,\n		dbo.Dim_equipe.Equipe\nFROM	dbo.Dim_equipe";

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
							row3.equipe_id = 0;
						} else {

							row3.equipe_id = rs_tDBInput_13.getInt(1);
							if (rs_tDBInput_13.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_13 < 2) {
							row3.Equipe = null;
						} else {

							tmpContent_tDBInput_13 = rs_tDBInput_13.getString(2);
							if (tmpContent_tDBInput_13 != null) {
								if (talendToDBList_tDBInput_13.contains(
										rsmd_tDBInput_13.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row3.Equipe = FormatterUtils.formatUnwithE(tmpContent_tDBInput_13);
								} else {
									row3.Equipe = tmpContent_tDBInput_13;
								}
							} else {
								row3.Equipe = null;
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
						 * [tAdvancedHash_row3 main ] start
						 */

						currentComponent = "tAdvancedHash_row3";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row3"

							);
						}

						row3Struct row3_HashRow = new row3Struct();

						row3_HashRow.equipe_id = row3.equipe_id;

						row3_HashRow.Equipe = row3.Equipe;

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
				 * [tDBInput_13 finally ] start
				 */

				currentComponent = "tDBInput_13";

				/**
				 * [tDBInput_13 finally ] stop
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

		globalMap.put("tDBInput_13_SUBPROCESS_STATE", 1);
	}

	public static class row11Struct implements routines.system.IPersistableComparableLookupRow<row11Struct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];
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
			final row11Struct other = (row11Struct) obj;

			if (this.Client_Prospect == null) {
				if (other.Client_Prospect != null)
					return false;

			} else if (!this.Client_Prospect.equals(other.Client_Prospect))

				return false;

			return true;
		}

		public void copyDataTo(row11Struct other) {

			other.Client_Prospect_id = this.Client_Prospect_id;
			other.Client_Prospect = this.Client_Prospect;

		}

		public void copyKeysDataTo(row11Struct other) {

			other.Client_Prospect = this.Client_Prospect;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Client_Prospect = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

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
		public int compareTo(row11Struct other) {

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
				// source node:tDBInput_15 - inputs:(after_tFileInputExcel_1)
				// outputs:(row11,row11) | target node:tAdvancedHash_row11 - inputs:(row11)
				// outputs:()
				// linked node: tMap_2 - inputs:(conclusion3,row6,row12,row4,row3,row11,row9)
				// outputs:(orange_RDV)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row11 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row11Struct> tHash_Lookup_row11 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row11Struct>getLookup(matchingModeEnum_row11);

				globalMap.put("tHash_Lookup_row11", tHash_Lookup_row11);

				/**
				 * [tAdvancedHash_row11 begin ] stop
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
						"enc:routine.encryption.key.v1:LNr37ZBfLKd7Qt2haqQLAhtIdVZ/tg8wTlMDwoS58KlBvFNHgKM=");

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

				String dbquery_tDBInput_15 = "SELECT dbo.Dim_Raison_sociale.Client_Prospect_id,\n		dbo.Dim_Raison_sociale.Client_Prospect\nFROM	dbo.Dim_Raison_sociale";

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
							row11.Client_Prospect_id = 0;
						} else {

							row11.Client_Prospect_id = rs_tDBInput_15.getInt(1);
							if (rs_tDBInput_15.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_15 < 2) {
							row11.Client_Prospect = null;
						} else {

							tmpContent_tDBInput_15 = rs_tDBInput_15.getString(2);
							if (tmpContent_tDBInput_15 != null) {
								if (talendToDBList_tDBInput_15.contains(
										rsmd_tDBInput_15.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row11.Client_Prospect = FormatterUtils.formatUnwithE(tmpContent_tDBInput_15);
								} else {
									row11.Client_Prospect = tmpContent_tDBInput_15;
								}
							} else {
								row11.Client_Prospect = null;
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
						 * [tAdvancedHash_row11 main ] start
						 */

						currentComponent = "tAdvancedHash_row11";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "row11"

							);
						}

						row11Struct row11_HashRow = new row11Struct();

						row11_HashRow.Client_Prospect_id = row11.Client_Prospect_id;

						row11_HashRow.Client_Prospect = row11.Client_Prospect;

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
				 * [tDBInput_15 finally ] start
				 */

				currentComponent = "tDBInput_15";

				/**
				 * [tDBInput_15 finally ] stop
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

		globalMap.put("tDBInput_15_SUBPROCESS_STATE", 1);
	}

	public static class row9Struct implements routines.system.IPersistableComparableLookupRow<row9Struct> {
		final static byte[] commonByteArrayLock_ORANGE_RDV = new byte[0];
		static byte[] commonByteArray_ORANGE_RDV = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int Categorie_id;

		public int getCategorie_id() {
			return this.Categorie_id;
		}

		public String Categorie;

		public String getCategorie() {
			return this.Categorie;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Categorie == null) ? 0 : this.Categorie.hashCode());

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

			if (this.Categorie == null) {
				if (other.Categorie != null)
					return false;

			} else if (!this.Categorie.equals(other.Categorie))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other.Categorie_id = this.Categorie_id;
			other.Categorie = this.Categorie;

		}

		public void copyKeysDataTo(row9Struct other) {

			other.Categorie = this.Categorie;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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
				if (length > commonByteArray_ORANGE_RDV.length) {
					if (length < 1024 && commonByteArray_ORANGE_RDV.length == 0) {
						commonByteArray_ORANGE_RDV = new byte[1024];
					} else {
						commonByteArray_ORANGE_RDV = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ORANGE_RDV, 0, length);
				strReturn = new String(commonByteArray_ORANGE_RDV, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ORANGE_RDV) {

				try {

					int length = 0;

					this.Categorie = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Categorie, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Categorie, dos);

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

				this.Categorie_id = dis.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Categorie_id = objectIn.readInt();

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.Categorie_id);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.Categorie_id);

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
			sb.append("Categorie_id=" + String.valueOf(Categorie_id));
			sb.append(",Categorie=" + Categorie);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Categorie, other.Categorie);
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
				// linked node: tMap_2 - inputs:(conclusion3,row6,row12,row4,row3,row11,row9)
				// outputs:(orange_RDV)

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
						"enc:routine.encryption.key.v1:px4VkndWv9jo/wnK4Ui70l8Un4IHNE0c3ZsiZGQOfyw7xVuSWRk=");

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

				String dbquery_tDBInput_17 = "SELECT dbo.\"Dim_Catégorie_rdv\".\"Catégorie_id\",\n		dbo.\"Dim_Catégorie_rdv\".Categorie\nFROM	dbo.\"Dim_Catégorie_rdv\""
						+ "";

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
							row9.Categorie_id = 0;
						} else {

							row9.Categorie_id = rs_tDBInput_17.getInt(1);
							if (rs_tDBInput_17.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_17 < 2) {
							row9.Categorie = null;
						} else {

							tmpContent_tDBInput_17 = rs_tDBInput_17.getString(2);
							if (tmpContent_tDBInput_17 != null) {
								if (talendToDBList_tDBInput_17.contains(
										rsmd_tDBInput_17.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row9.Categorie = FormatterUtils.formatUnwithE(tmpContent_tDBInput_17);
								} else {
									row9.Categorie = tmpContent_tDBInput_17;
								}
							} else {
								row9.Categorie = null;
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

						row9_HashRow.Categorie_id = row9.Categorie_id;

						row9_HashRow.Categorie = row9.Categorie;

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
		final RDV RDVClass = new RDV();

		int exitCode = RDVClass.runJobInTOS(args);

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
			java.io.InputStream inContext = RDV.class.getClassLoader()
					.getResourceAsStream("orange/rdv_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = RDV.class.getClassLoader()
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
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : RDV");
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
 * 236565 characters generated by Talend Open Studio for Data Integration on the
 * 12 mai 2022 à 19:24:02 CEST
 ************************************************************************************************/