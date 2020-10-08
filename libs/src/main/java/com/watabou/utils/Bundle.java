/*
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.watabou.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PushbackInputStream;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Bundle {

	private static final String CLASS_NAME = "__className";
	
	private static HashMap<String,String> aliases = new HashMap<String, String>();
	
	private JSONObject data;
	
	public Bundle() {
		this( new JSONObject() );
	}
	
	public String toString() {
		return data.toString();
	}
	
	private Bundle( JSONObject data ) {
		this.data = data;
	}
	
	public boolean isNull() {
		return data == null;
	}
	
	public ArrayList<String> fields() {
		ArrayList<String> result = new ArrayList<String>();
		
		@SuppressWarnings("unchecked")
		Iterator<String> iterator = data.keys();
		while (iterator.hasNext()) {
			result.add( iterator.next() );
		}
		
		return result;
	}
	
	public boolean contains( String key ) {
		return !data.isNull( key );
	}
	
	public boolean getBoolean( String key ) {
		return data.optBoolean( key );
	}
	
	public boolean getBoolean( String key, boolean defaultValue ) {
		return data.optBoolean( key, defaultValue );
	}	
	
	public int getInt( String key ) {
		return data.optInt( key );
	}
	
	public int getInt( String key, int defaultValue ) {
		return data.optInt( key, defaultValue );
	}
	
	
	public float getFloat( String key ) {
		return (float)data.optDouble( key );
	}
	
	public String getString( String key ) {
		return data.optString( key );
	}
	
	public Bundle getBundle( String key ) {
		return new Bundle( data.optJSONObject( key ) );
	}
	
	public Class getClass( String key ) {
		String clName =  getString(key).replace("class ", "");
        if (clName != null){
			if (aliases.containsKey( clName )) {
				clName = aliases.get( clName );
			}
			try {
				Class cl = Class.forName( clName );
				return cl;
			} catch (ClassNotFoundException e) {
				//reportException(e);
				return null;
			}
		}
		return null;
	}	
	
	private Bundlable get() {
		if (data == null) return null;
		try {
			String clName = getString( CLASS_NAME );
			if (aliases.containsKey( clName )) {
				clName = aliases.get( clName );
			}
			
			Class<?> cl = Class.forName( clName );
			if (cl != null && (!cl.isMemberClass() || Modifier.isStatic(cl.getModifiers()))) {
				Bundlable object = (Bundlable)cl.newInstance();
				object.restoreFromBundle( this );
				return object;
			} else {
				return null;
			}
		} catch (Exception e) {
			e = null;
			return null;
		}
	}
	
	public Bundlable get( String key ) {
		return getBundle( key ).get();	
	}
	
	public <E extends Enum<E>> E getEnum( String key, Class<E> enumClass ) {
		try {
			return Enum.valueOf( enumClass, data.getString( key ) );
		} catch (JSONException e) {
			return enumClass.getEnumConstants()[0];
		}
	}
	
	public int[] getIntArray( String key ) {
		try {
			JSONArray array = data.getJSONArray( key );
			int length = array.length();
			int[] result = new int[length];
			for (int i=0; i < length; i++) {
				result[i] = array.getInt( i );
			}
			return result;
		} catch (JSONException e) {
			return null;
		}
	}
	
	public boolean[] getBooleanArray( String key ) {
		try {
			JSONArray array = data.getJSONArray( key );
			int length = array.length();
			boolean[] result = new boolean[length];
			for (int i=0; i < length; i++) {
				result[i] = array.getBoolean( i );
			}
			return result;
		} catch (JSONException e) {
			return null;
		}
	}
	
	public String[] getStringArray( String key ) {
		try {
			JSONArray array = data.getJSONArray( key );
			int length = array.length();
			String[] result = new String[length];
			for (int i=0; i < length; i++) {
				result[i] = array.getString( i );
			}
			return result;
		} catch (JSONException e) {
			return null;
		}
	}

	public Class[] getClassArray( String key ) {
		try {
			JSONArray array = data.getJSONArray( key );
			int length = array.length();
			Class[] result = new Class[length];
			for (int i=0; i < length; i++) {
				String clName = array.getString( i ).replace("class ", "");
				if (aliases.containsKey( clName )) {
					clName = aliases.get( clName );
				}
				try {
					Class cl = Class.forName( clName );
					result[i] = cl;
				} catch (ClassNotFoundException e) {
					//reportException(e);
					result[i] = null;
				}
			}
			return result;
		} catch (JSONException e) {
			//reportException(e);
			return null;
		}
	}
	
	public Collection<Bundlable> getCollection( String key ) {
		
		ArrayList<Bundlable> list = new ArrayList<Bundlable>();
		
		try {
			JSONArray array = data.getJSONArray( key );
			for (int i=0; i < array.length(); i++) {
				list.add( new Bundle( array.getJSONObject( i ) ).get() );
			}
		} catch (JSONException e) {
			
		}
		
		return list;
	}
	
	public void put( String key, boolean value ) {
		try {
			data.put( key, value );
		} catch (JSONException e) {

		}
	}
	
	public void put( String key, int value ) {
		try {
			data.put( key, value );
		} catch (JSONException e) {

		}
	}
	
	public void put( String key, float value ) {
		try {
			data.put( key, value );
		} catch (JSONException e) {

		}
	}
	
	public void put( String key, String value ) {
		try {
			data.put( key, value );
		} catch (JSONException e) {

		}
	}
	
	public void put( String key, Bundle bundle ) {
		try {
			data.put( key, bundle.data );
		} catch (JSONException e) {

		}
		
	}
	
	public void put( String key, Class value ){
		try {
			data.put( key, value );
		} catch (JSONException e) {
			//reportException(e);
		}
	}
	
	public void put( String key, Bundlable object ) {
		if (object != null) {
			try {
				Bundle bundle = new Bundle();
				bundle.put( CLASS_NAME, object.getClass().getName() );
				object.storeInBundle( bundle );
				data.put( key, bundle.data );
			} catch (JSONException e) {
			}
		}
	}
	
	public void put( String key, Enum<?> value ) {
		if (value != null) {
			try {
				data.put( key, value.name() );
			} catch (JSONException e) {
			}
		}
	}
	
	public void put( String key, int[] array ) {
		try {
			JSONArray jsonArray = new JSONArray();
			for (int i=0; i < array.length; i++) {
				jsonArray.put( i, array[i] );
			}
			data.put( key, jsonArray );
		} catch (JSONException e) {
			
		}
	}
	
	public void put( String key, boolean[] array ) {
		try {
			JSONArray jsonArray = new JSONArray();
			for (int i=0; i < array.length; i++) {
				jsonArray.put( i, array[i] );
			}
			data.put( key, jsonArray );
		} catch (JSONException e) {
			
		}
	}

	public void put( String key, String[] array ) {
		try {
			JSONArray jsonArray = new JSONArray();
			for (int i=0; i < array.length; i++) {
				jsonArray.put( i, array[i] );
			}
			data.put( key, jsonArray );
		} catch (JSONException e) {
		
		}
	}
	public void put( String key, Class[] array ){
		try {
			JSONArray jsonArray = new JSONArray();
			for (int i=0; i < array.length; i++) {
				jsonArray.put( i, array[i] );
			}
			data.put( key, jsonArray );
		} catch (JSONException e) {
			//reportException(e);
		}
	}	
	
	public void put( String key, Collection<? extends Bundlable> collection ) {
		JSONArray array = new JSONArray();
		for (Bundlable object : collection) {
			//Skip none-static inner classes as they can't be instantiated through bundle restoring
			//Classes which make use of none-static inner classes must manage instantiation manually
			if (object != null) {
				Class cl = object.getClass();
				if (!cl.isMemberClass() || Modifier.isStatic(cl.getModifiers())) {
					Bundle bundle = new Bundle();
					bundle.put(CLASS_NAME, cl.getName());
					object.storeInBundle(bundle);
					array.put(bundle.data);
				}
			}
		}
		try {
			data.put( key, array );
		} catch (JSONException e) {
			//reportException(e);
		}
	}
	
	public static Bundle read( InputStream stream ) {
		
		try {
			BufferedReader reader = new BufferedReader( new InputStreamReader( stream ) );
			
			StringBuilder all = new StringBuilder();
			String line = reader.readLine();
			while (line != null) {
				all.append( line );
				line = reader.readLine();
			}
			
			JSONObject json = (JSONObject)new JSONTokener( all.toString() ).nextValue();
			reader.close();
			
			return new Bundle( json );
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Bundle read( byte[] bytes ) {
		try {
			
			JSONObject json = (JSONObject)new JSONTokener( new String( bytes ) ).nextValue();
			return new Bundle( json );
			
		} catch (JSONException e) {
			return null;
		}
	}
	
	public static boolean write( Bundle bundle, OutputStream stream ) {
		try {
			BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( stream ) );	
			writer.write( bundle.data.toString() );
			writer.close();
			
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	public static void addAlias( Class<?> cl, String alias ) {
		aliases.put( alias, cl.getName() );
	}
	//This may be set in order to have bundles report exceptions
	//...Yes it would be far cleaner to have the bundling methods throw exceptions
	//But that would require too much code-changing right now.
	public static BundleExceptionCallback exceptionReporter;

	private static void reportException(Throwable t){
		if (exceptionReporter != null){
			exceptionReporter.call(t);
		}
	}


	public static abstract class BundleExceptionCallback {
		public abstract void call(Throwable t);
	}	
}