package com.ht.yikecrm.util.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.ezmorph.object.AbstractObjectMorpher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class DateMorpher extends AbstractObjectMorpher {
	protected static final Log LOG = LogFactory.getLog(JsonFormat.class);
	
	@SuppressWarnings("deprecation")
	@Override
	public Object morph(Object arg0) {
		// TODO Auto-generated method stub
		Date date = null;
		if (arg0 != null) {
			String s = arg0.toString();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss'Z'");
			try {
				date = format.parse(s);
				Calendar ca = Calendar.getInstance();
				ca.setTime(date);
				if (ca.get(Calendar.YEAR) < 1980) {
					ca.add(Calendar.YEAR, 10);
				}
				ca.add(Calendar.HOUR_OF_DAY, 8);
				date = ca.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				try {						
					date = new Date(s);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					LOG.debug("=========Not a date type========" + e1);
					return arg0;
				}
			}
		}
		return date;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class morphsTo() {
		// TODO Auto-generated method stub
		return Date.class;
	}

}
