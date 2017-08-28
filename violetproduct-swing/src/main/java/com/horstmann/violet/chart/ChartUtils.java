package com.horstmann.violet.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

/**
 * Jfreechart������
 * <p>
 * ���������������<br>
 * �����������ͼ�����ݼ���������ͼ���ݼ���ʱ������ͼ���ݼ�<br>
 * ��������״ͼ������ͼ����ͼ���ѻ���״ͼ��ʱ������ͼ����ʽ������Ⱦ<br>
 * ����X-Y��������ʽ
 * <p>
 * 
 * 
 * @author chenchangwen
 * @since:2014-2-18
 * 
 */
public class ChartUtils {
	private static String NO_DATA_MSG = "���ݼ���ʧ��";
	private static Font FONT = new Font("����", Font.PLAIN, 12);
//	public static Color[] CHART_COLORS = {
//			new Color(31,129,188), new Color(92,92,97), new Color(144,237,125), new Color(255,188,117),
//			new Color(153,158,255), new Color(255,117,153), new Color(253,236,109), new Color(128,133,232),
//			new Color(158,90,102),new Color(255, 204, 102) };// ��ɫ
	public static Color[] CHART_COLORS = { new Color(76, 129, 189), new Color(168, 69, 66), new Color(135, 163, 77),
			new Color(112, 87, 141), new Color(64, 150, 173), new Color(217, 130, 60), new Color(145, 167, 205),
			new Color(207, 145, 144) };// ��ɫ
	public static Color[] PIE_CHART_LINE_COLORS = { new Color(46, 99, 159), new Color(118, 39, 36), new Color(105, 113, 47),
			new Color(82, 57, 111), new Color(34, 100, 143), new Color(187, 100, 30), new Color(115, 137, 175),
			new Color(177, 115, 114) };// ��ɫ
	static {
		setChartTheme();
	}

	public ChartUtils() {
	}

	/**
	 * ����������ʽ �������
	 */
	public static void setChartTheme() {
		// ��������������ʽ �������
		StandardChartTheme chartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		chartTheme.setExtraLargeFont(FONT);
		// ����ͼ��������
		chartTheme.setRegularFont(FONT);
		// �������������
		chartTheme.setLargeFont(FONT);
		chartTheme.setSmallFont(FONT);
		chartTheme.setTitlePaint(new Color(51, 51, 51));
		chartTheme.setSubtitlePaint(new Color(85, 85, 85));

		chartTheme.setLegendBackgroundPaint(Color.WHITE);// ���ñ�ע
		chartTheme.setLegendItemPaint(Color.BLACK);//
		chartTheme.setChartBackgroundPaint(Color.WHITE);
		// ������ɫ������ɫ.������Ӧ��
		// paintSequence,outlinePaintSequence,strokeSequence,outlineStrokeSequence,shapeSequence

		Paint[] OUTLINE_PAINT_SEQUENCE = new Paint[] { Color.WHITE};
		// ��������ɫԴ
		DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(CHART_COLORS, CHART_COLORS, PIE_CHART_LINE_COLORS,
				DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
				DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
		chartTheme.setDrawingSupplier(drawingSupplier);

		chartTheme.setPlotBackgroundPaint(Color.WHITE);// ��������
		chartTheme.setPlotOutlinePaint(Color.WHITE);// ����������߿�
		chartTheme.setLabelLinkPaint(new Color(8, 55, 114));// ���ӱ�ǩ��ɫ
		chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

//		chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));
		chartTheme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
//		chartTheme.set
		chartTheme.setDomainGridlinePaint(new Color(192, 208, 224));// X�����ᴹֱ������ɫ
		chartTheme.setRangeGridlinePaint(new Color(192, 192, 192));// Y������ˮƽ������ɫ

		chartTheme.setBaselinePaint(Color.WHITE);
		chartTheme.setCrosshairPaint(Color.BLUE);// ��ȷ������
		chartTheme.setAxisLabelPaint(new Color(51, 51, 51));// ���������������ɫ
		chartTheme.setTickLabelPaint(new Color(67, 67, 72));// �̶�����
		chartTheme.setBarPainter(new StandardBarPainter());// ������״ͼ��Ⱦ
		chartTheme.setXYBarPainter(new StandardXYBarPainter());// XYBar ��Ⱦ

		chartTheme.setItemLabelPaint(Color.black);
		chartTheme.setThermometerPaint(Color.white);// �¶ȼ�

		ChartFactory.setChartTheme(chartTheme);
	}

	/**
	 * ���������ı������
	 */
	public static void setAntiAlias(JFreeChart chart) {
		chart.setTextAntiAlias(false);

	}

	/**
	 * ����ͼ���ޱ߿�Ĭ�Ϻ�ɫ�߿�
	 */
	public static void setLegendEmptyBorder(JFreeChart chart) {
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

	}

	/**
	 * ����������ݼ���
	 */
	public static DefaultCategoryDataset createDefaultCategoryDataset(Vector<Serie> series, String[] categories) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Serie serie : series) {
			String name = serie.getName();
			Vector<Object> data = serie.getData();
			if (data != null && categories != null && data.size() == categories.length) {
				for (int index = 0; index < data.size(); index++) {
					String value = data.get(index) == null ? "" : data.get(index).toString();
					if (isPercent(value)) {
						value = value.substring(0, value.length() - 1);
					}
					if (isNumber(value)) {
						dataset.setValue(Double.parseDouble(value), name, categories[index]);
					}
				}
			}

		}
		return dataset;

	}

	/**
	 * ������ͼ���ݼ���
	 */
	public static DefaultPieDataset createDefaultPieDataset(String[] categories, Object[] datas) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (int i = 0; i < categories.length && categories != null; i++) {
			String value = datas[i].toString();
			if (isPercent(value)) {
				value = value.substring(0, value.length() - 1);
			}
			if (isNumber(value)) {
				dataset.setValue(categories[i], Double.valueOf(value));
			}
		}
		return dataset;

	}

	/**
	 * ����ʱ����������
	 * 
	 * @param category
	 *            ���
	 * @param dateValues
	 *            ����-ֵ ����
	 * @param xAxisTitle
	 *            X���������
	 * @return
	 */
	public static TimeSeries createTimeseries(String category, Vector<Object[]> dateValues) {
		TimeSeries timeseries = new TimeSeries(category);

		if (dateValues != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			for (Object[] objects : dateValues) {
				Date date = null;
				try {
					date = dateFormat.parse(objects[0].toString());
				} catch (ParseException e) {
				}
				String sValue = objects[1].toString();
				double dValue = 0;
				if (date != null && isNumber(sValue)) {
					dValue = Double.parseDouble(sValue);
					timeseries.add(new Day(date), dValue);
				}
			}
		}

		return timeseries;
	}

	/**
	 * ���� ����ͼ��ʽ
	 * 
	 * @param plot
	 * @param isShowDataLabels
	 *            �Ƿ���ʾ���ݱ�ǩ Ĭ�ϲ���ʾ�ڵ���״
	 */
	public static void setLineRender(CategoryPlot plot, boolean isShowDataLabels) {
		setLineRender(plot, isShowDataLabels, false);
	}

	/**
	 * ��������ͼ��ʽ
	 * 
	 * @param plot
	 * @param isShowDataLabels
	 *            �Ƿ���ʾ���ݱ�ǩ
	 */
	public static void setLineRender(CategoryPlot plot, boolean isShowDataLabels, boolean isShapesVisible) {
		plot.setNoDataMessage(NO_DATA_MSG);
		plot.setInsets(new RectangleInsets(10, 10, 0, 10), false);
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

//		renderer.setStroke(new BasicStroke(1.5F));
		renderer.setStroke(new BasicStroke(3F));
		renderer.setOutlineStroke(new BasicStroke(5F));
		if (isShowDataLabels) {
			renderer.setBaseItemLabelsVisible(true);
			renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator(StandardCategoryItemLabelGenerator.DEFAULT_LABEL_FORMAT_STRING,
					NumberFormat.getInstance()));
			renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.BOTTOM_CENTER));// weizhi
		}
		renderer.setBaseShapesVisible(isShapesVisible);// ���ݵ������״
//		renderer.setS
		
		setXAixs(plot);
		setYAixs(plot);

	}

	/**
	 * ����ʱ������ͼ��ʽ
	 * 
	 * @param plot
	 * @param isShowData
	 *            �Ƿ���ʾ����
	 * @param isShapesVisible
	 *            �Ƿ���ʾ���ݽڵ���״
	 */
//	public static void setTimeSeriesRender(Plot plot, boolean isShowData, boolean isShapesVisible) {
//
//		XYPlot xyplot = (XYPlot) plot;
//		xyplot.setNoDataMessage(NO_DATA_MSG);
//		xyplot.setInsets(new RectangleInsets(10, 10, 5, 10));
//
//		XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
//
//		xyRenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
//		xyRenderer.setBaseShapesVisible(false);
//		if (isShowData) {
//			xyRenderer.setBaseItemLabelsVisible(true);
//			xyRenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
//			xyRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE1, TextAnchor.BOTTOM_CENTER));// weizhi
//		}
//		xyRenderer.setBaseShapesVisible(isShapesVisible);// ���ݵ������״
//
//		DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
//		domainAxis.setAutoTickUnitSelection(false);
//		DateTickUnit dateTickUnit = new DateTickUnit(DateTickUnitType.YEAR, 1, new SimpleDateFormat("yyyy-MM")); // �ڶ���������ʱ������
//		domainAxis.setTickUnit(dateTickUnit);
//
//		StandardXYToolTipGenerator xyTooltipGenerator = new StandardXYToolTipGenerator("{1}:{2}", new SimpleDateFormat("yyyy-MM-dd"), new DecimalFormat("0"));
//		xyRenderer.setBaseToolTipGenerator(xyTooltipGenerator);
//
//		setXY_XAixs(xyplot);
//		setXY_YAixs(xyplot);
//
//	}

	/**
	 * ����ʱ������ͼ��ʽ -Ĭ�ϲ���ʾ���ݽڵ���״
	 * 
	 * @param plot
	 * @param isShowData
	 *            �Ƿ���ʾ����
	 */

//	public static void setTimeSeriesRender(Plot plot, boolean isShowData) {
//		setTimeSeriesRender(plot, isShowData, false);
//	}

	/**
	 * ����ʱ������ͼ��Ⱦ�����Ǵ���һ�����⣺���timeseries����������ǰ�������֯�� ��ô���ӵĿ��Ȼ�ǳ�С����ֱ��һ����ϸ
	 * 
	 * @param plot
	 * @param isShowDataLabels
	 */

	public static void setTimeSeriesBarRender(Plot plot, boolean isShowDataLabels) {

		XYPlot xyplot = (XYPlot) plot;
		xyplot.setNoDataMessage(NO_DATA_MSG);

		XYBarRenderer xyRenderer = new XYBarRenderer(0.1D);
		xyRenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());

		if (isShowDataLabels) {
			xyRenderer.setBaseItemLabelsVisible(true);
			xyRenderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		}

		StandardXYToolTipGenerator xyTooltipGenerator = new StandardXYToolTipGenerator("{1}:{2}", new SimpleDateFormat("yyyy-MM-dd"), new DecimalFormat("0"));
		xyRenderer.setBaseToolTipGenerator(xyTooltipGenerator);
		setXY_XAixs(xyplot);
		setXY_YAixs(xyplot);

	}

	/**
	 * ������״ͼ��Ⱦ
	 * 
	 * @param plot
	 * @param isShowDataLabels
	 */
	public static void setBarRenderer(CategoryPlot plot, boolean isShowDataLabels) {

		plot.setNoDataMessage(NO_DATA_MSG);
		plot.setInsets(new RectangleInsets(10, 10, 5, 10));
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setMaximumBarWidth(0.075);// ��������������
		
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelFont(new Font("΢���ź�", Font.PLAIN, 13));
		renderer.setBaseItemLabelPaint(new Color(0, 0, 0));

		if (isShowDataLabels) {
			renderer.setBaseItemLabelsVisible(true);
		}

		setXAixs(plot);
		setYAixs(plot);
	}

	/**
	 * ���öѻ���״ͼ��Ⱦ
	 * 
	 * @param plot
	 */

	public static void setStackBarRender(CategoryPlot plot) {
		plot.setNoDataMessage(NO_DATA_MSG);
		plot.setInsets(new RectangleInsets(10, 10, 5, 10));
		StackedBarRenderer renderer = (StackedBarRenderer) plot.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		plot.setRenderer(renderer);
		setXAixs(plot);
		setYAixs(plot);
	}

	/**
	 * �������ͼ��(CategoryPlot) X������������ɫ����ʽ
	 * 
	 * @param axis
	 */
	public static void setXAixs(CategoryPlot plot) {
		Color lineColor = new Color(31, 121, 170);
		
		CategoryAxis dateaxis=plot.getDomainAxis();
		dateaxis.setLowerMargin(0.01D);
        dateaxis.setUpperMargin(0.01D);
//		plot.getDomainAxis().setAxisLinePaint(lineColor);// X��������ɫ
//		plot.getDomainAxis().setTickMarkPaint(lineColor);// X��������|������ɫ

	}

	/**
	 * �������ͼ��(CategoryPlot) Y������������ɫ����ʽ ͬʱ��ֹ�����޷���ʾ
	 * 
	 * @param axis
	 */
	public static void setYAixs(CategoryPlot plot) {
		Color lineColor = new Color(192, 208, 224);
		ValueAxis axis = plot.getRangeAxis();
//		axis.setAxisLinePaint(lineColor);// Y��������ɫ
//		axis.setTickMarkPaint(lineColor);// Y��������|������ɫ
		// ����Y�̶�
//		axis.setAxisLineVisible(false);
//		axis.setTickMarksVisible(false);
		// Y����������
		plot.setRangeGridlinePaint(new Color(192, 192, 192));
//		plot.setRangeGridlineStroke(new BasicStroke(1));

		plot.getRangeAxis().setUpperMargin(0.1);// ���ö���Y��������,��ֹ�����޷���ʾ
		plot.getRangeAxis().setLowerMargin(0.1);// ���õײ�Y��������

	}

	/**
	 * ����XYͼ��(XYPlot) X������������ɫ����ʽ
	 * 
	 * @param axis
	 */
	public static void setXY_XAixs(XYPlot plot) {
		Color lineColor = new Color(31, 121, 170);
		plot.getDomainAxis().setAxisLinePaint(lineColor);// X��������ɫ
		plot.getDomainAxis().setTickMarkPaint(lineColor);// X��������|������ɫ

	}

	/**
	 * ����XYͼ��(XYPlot) Y������������ɫ����ʽ ͬʱ��ֹ�����޷���ʾ
	 * 
	 * @param axis
	 */
	public static void setXY_YAixs(XYPlot plot) {
		Color lineColor = new Color(192, 208, 224);
		ValueAxis axis = plot.getRangeAxis();
		axis.setAxisLinePaint(lineColor);// X��������ɫ
		axis.setTickMarkPaint(lineColor);// X��������|������ɫ
		// ����Y�̶�
		axis.setAxisLineVisible(false);
		axis.setTickMarksVisible(false);
		// Y����������
		plot.setRangeGridlinePaint(new Color(192, 192, 192));
		plot.setRangeGridlineStroke(new BasicStroke(1));
		plot.setDomainGridlinesVisible(false);

		plot.getRangeAxis().setUpperMargin(0.12);// ���ö���Y��������,��ֹ�����޷���ʾ
		plot.getRangeAxis().setLowerMargin(0.12);// ���õײ�Y��������

	}

	/**
	 * ���ñ�״ͼ��Ⱦ
	 */
	public static void setPieRender(Plot plot) {

		plot.setNoDataMessage(NO_DATA_MSG);
		plot.setInsets(new RectangleInsets(10, 10, 5, 10));
		PiePlot piePlot = (PiePlot) plot;
		piePlot.setInsets(new RectangleInsets(45, 0, 45, 0));
//		piePlot.setCircular(true);// Բ��
		piePlot.setCircular(false);// ��Բ��
		
		PiePlot3D piePlot3D=(PiePlot3D)plot;
		piePlot3D.setDepthFactor(0.1f);//3D��ͼ��Z��߶ȣ�0.0��1.0��
		
		piePlot.setAutoPopulateSectionOutlinePaint(true);

		// piePlot.setSimpleLabels(true);// �򵥱�ǩ
		piePlot.setLabelGap(0.01);
		piePlot.setInteriorGap(0.05D);
		piePlot.setLegendItemShape(new Rectangle(10, 10));// ͼ����״
		piePlot.setIgnoreNullValues(true);
		piePlot.setLabelBackgroundPaint(null);// ȥ������ɫ
		piePlot.setLabelShadowPaint(null);// ȥ����Ӱ
		piePlot.setLabelOutlinePaint(null);// ȥ���߿�
		piePlot.setShadowPaint(null);
		// 0:category 1:value:2 :percentage
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{2}"));// ��ʾ��ǩ����
	}

	/**
	 * �ǲ���һ��%��ʽ�İٷֱ�
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPercent(String str) {
		return str != null ? str.endsWith("%") && isNumber(str.substring(0, str.length() - 1)) : false;
	}

	/**
	 * �ǲ���һ������
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return str != null ? str.matches("^[-+]?(([0-9]+)((([.]{0})([0-9]*))|(([.]{1})([0-9]+))))$") : false;
	}

}