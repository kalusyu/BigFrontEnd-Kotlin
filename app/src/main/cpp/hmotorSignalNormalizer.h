#pragma once

#include <vector>
#include <math.h>

#define _TT_DEMO_ 0
#if _TT_DEMO_

#include <stdio.h>
#include <string>
#include <iostream>
#include <fstream>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/highgui/highgui.hpp>

#endif


class iexpMovingAverage
{
	bool mbEmpty;
	float mAlpha, mret;
public:
	iexpMovingAverage() { mbEmpty = true; }
	~iexpMovingAverage() {}

	void reset() { mbEmpty = true; mret = 0.0f; }
	void create(float len, float eps = 1e-2f)
	{
		if (len < 1.0f)	len = 1.0f;
		mAlpha = eps <= 0.0f ? 3.45f / (len + 1) : 1.0f - exp(log(eps) / len);
		reset();
	}
	bool empty() { return mbEmpty; }
	float append(float val)
	{
		if (mbEmpty) { mret = val;	mbEmpty = false; }
		else			mret = mret + (val - mret) * mAlpha;
		return mret;
	}
	float output() { return mret; }
};
class iwgtMovingFilter
{
	float mswgt, mavg, mvar;
	bool mbStart;
public:
	iwgtMovingFilter() { start(); }
	~iwgtMovingFilter() {}

	void start()
	{
		mbStart = true; mavg = mvar = mswgt = 0;
	}
	void update(float val, float wgt)
	{
		if (wgt <= 0.0f)	return;
		mswgt += wgt;
		if (mbStart)
		{
			mavg = val; mvar = 0;	mbStart = false;
		}
		else
		{
			float newAvg = mavg + (val - mavg) * wgt / mswgt;
			mvar = mvar + wgt * (val - mavg) * (val - newAvg);
			mavg = newAvg;
		}
	}
	float mean() { return mavg; }
	float stdev() { return mswgt <= 0.0f ? 0.0f : sqrt(mvar / mswgt); }
	float wtotal() { return mswgt; }
};

class motorSignalNormalizer
{
	float m_refDist;

	iexpMovingAverage m_ema_rlive[3];
	iexpMovingAverage m_ema_upper;
	iwgtMovingFilter m_wma_long;

public:
	motorSignalNormalizer() { m_refDist = 180000.0f; }
	~motorSignalNormalizer() {}

	void start(int refDist = -1)
	{
		m_refDist = refDist > 0 ? (float)refDist : 111240.0f;
		m_ema_rlive[0].create(3.0f, 0.01f);
		m_ema_rlive[1].create(2.5f, 0.05f);
		m_ema_rlive[2].create(1.5f, 0.25f);

		m_ema_upper.create(20.0f);
		m_wma_long.start();

		m_wma_long.update(m_refDist, 3.0f);
		m_ema_upper.append(m_refDist);
	}

	float update(float liveDist)
	{
		float upper = m_ema_upper.output();
		if (liveDist > 1.0f)
		{
			m_wma_long.update(liveDist, 1.0f);
			upper = m_ema_upper.append(m_wma_long.mean() + 1.3f * m_wma_long.stdev());
		}
		if (upper < (float)liveDist)
		{
			for (int k = 0; k < 3; k++)
				upper = m_ema_upper.append(liveDist);
		}

		float ratio = (float)liveDist / (upper + 1e-6f);
		for (int k = 0; k < 3; k++)
			ratio = m_ema_rlive[k].append(ratio);

		return ratio;
	}

#if _TT_DEMO_
	void ttdemo(std::string filename)
	{
		start();

		std::vector<int> vdist;
		load(vdist, filename);
		int id = 0;
		for (int i = 1; i < (int)vdist.size(); i++)
		{
			if (vdist[i] > vdist[id]) id = i;
		}
		
		std::vector<float> vraw;
		std::vector<float> vfit;
		for (int i = 0; i < (int)vdist.size(); i++)
		{
			vraw.push_back((float)vdist[i] / vdist[id]);

			vfit.push_back(update(vdist[i]));
			showCurve(vraw, vfit, 8, 80);
		}
	}
	void load(std::vector<int> &vout, std::string filename)
	{
		vout.clear();
		std::ifstream fs;
		fs.open(filename.c_str(), std::ios::in);
		if (!fs.is_open())	return;

		std::string sline, token;
		std::vector<std::string> vstr;
		std::stringstream ss;
		while (!fs.eof())
		{
			std::getline(fs, sline);
			ss.clear();
			ss.str(sline);

			std::string sub;
			std::getline(ss, sub, '}');
			sub = sub.substr(sub.find('{') + 1);

			ss.clear();
			ss.str(sub);
			vstr.clear();
			while (std::getline(ss, token, ':'))
			{
				vstr.push_back(token);
			}
			vout.push_back(std::atoi(vstr[3].c_str()));
		}
		fs.close();
	}
	void showCurve(std::vector<float> &vraw, std::vector<float> &vfit, int xskip, int usedLen)
	{
		cv::Mat xdsp = cv::Mat::zeros(256, usedLen * xskip, CV_8UC3);

		if (!vraw.empty())
		{
			drawCurve(xdsp, vraw, xskip, usedLen, CV_RGB(0, 255, 0), 2);
		}
		if (!vfit.empty())
		{
			drawCurve(xdsp, vfit, xskip, usedLen, CV_RGB(255, 0, 0), 2);
		}
		cv::imshow("xdsp", xdsp);
		cv::waitKey(100);
	}
	void drawCurve(cv::Mat &xout, std::vector<float> &vlist, int xskip, int usedLen, cv::Scalar color, int thickness)
	{
		int sp = (int)vlist.size() - usedLen;
		if (sp < 0)	sp = 0;

		float yfactor = xout.rows * 0.85f;
		int xp = 0;
		cv::Point pp, np;
		for (int i = sp; i < (int)vlist.size(); i++, xp += xskip)
		{
			np = cv::Point(xp, (int)(xout.rows - 1 - vlist[i] * yfactor));
			if (i > sp)
			{
				cv::line(xout, pp, np, color, thickness);
			}
			pp = np;
		}
	}
#endif
};
